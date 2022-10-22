【完整版本，可进入初赛评分】

团队名称：大明湖畔的小天台

作者：数据小黑、白菜帮帮主

项目进展：只有一篇RFC

项目代码仓库的链接: https://github.com/WholeWorld-Timothy/OneEntrance

# 项目介绍
应用只需要保持一个数据源，即可使用OLTP、轻度OLAP、重度OLAP等类型的数据库服务。

# 背景&动机
在运营一个电商网站的过程中，繁杂的数据源让配置管理非常困难，特别是维护多套环境，例如灰度、金丝雀、生产等多个集群时，这个问题尤其突出。从简化数据架构的角度出发，根据现有业务，构造一个数据入口，提供适当的注解，提供OLTP、轻度OLAP、重度OLAP等类型的数据库服务。本项目以TiDB为基础，辅以ShardingSphere、Flink、StarRocks等开源技术构建。

# 项目设计
## 项目背景
我们团队曾经在[传统行业数据架构发展变化](https://tidb.net/blog/62fd595e)中提到过，我们现在数据中台的架构如下：
![image](https://user-images.githubusercontent.com/4351491/195276832-74ef5406-615a-4202-aa0d-29d105b2be23.png)

从应用层看下层的数据源，有两个OLTP、一个OLAP、一个KV，共四个数据源，加上其他缓存、消息队列等中间件，配置文件长达100多行，维护和管理极其困难。

上述架构可以理解为“上个时代”的架构，但由于项目的历史包袱问题，我们直到今年才才开始预研进行架构改造。在评估过程中，我们深入研究了TiDB，决定选用TiDB作为核心架构解决OLTP的问题，TiDB有TiKV和TiFlash两个引擎，已经实现了一个入口解决OLTP和轻度OLAP的问题。但在测试中我们发现，对于一致性要求不强的场景，特别是一些T+1的报表，如果使用TiFlash进行离线计算，性能上稍有差异，测试了TiSpark也同样如此，究其原因主要是因为TiKV和TiFlash需要保持强一致的原因，数据需要首先写TiKV，再同步到TiFlash，这让性能受到限制。另外在对外数据接入方面，TiFlash也稍显逊色。我们决定扩展TiDB的架构，结合其他开源组件，实现高吞吐离线计算与查询。当然，我们这个方案也可能随着TiFlash的完善成熟而过时，但与其等不如现在就做。

## 架构设计
我们是一群“懒汉”，相信总有合适的开源工具可以使用。我们决定采用开源软件堆砌实现这个架构。架构图如下：
![image](https://user-images.githubusercontent.com/4351491/195276868-2a4acf5b-c3e1-4eca-a289-2166e9392b53.png)

在架构设计中，拓展了ShardingSphere、StarRocks的使用，作为架构的补充。
1. ShardingSphere作为统一入口存在，根据应用传入的Hint信息，切换查询到TiDB或者StarRocks上。
2. StarRocks作为离线计算落盘的存储底座，加速了数据计算过程。由于StarRocks具有强大的外表能力，为数据挖掘、科学计算等场景提供了统一数据入口，让数据架构变的简洁。
3. TiKV与StarRocks之间通过Flink搭建维表的实时同步通道，事实表的每日新增在离线计算时由Spark程序从TiFlash读取。

## 实现思路
为实现上述设计，需要有一定的开发，来简化整个搭建过程，目标是不需要做任何配置变更，一切都是自洽的、舒适的。整体研发工作作如下划分：
1. ShardingSphere层采用Jar的方式放入每个应用，在应用启动时，自动读取TiDB、StarRocks的所有表注册到ShardingSphere的配置中。
2. 实现两个切面：

    a. Control层切面：数据操作层切面，使用HintManager注入默认值，默认选择TiDB作为数据库。
    
    b. Service层注解切面：需要查询StarRocks的逻辑，主动添加注解，切面在执行逻辑前利用HintManager注入数据源ID，选择StarRocks作为查询数据源。
    
3. TiKV与StarRocks之间的同步采用Flink+Flink tidb connector与Flink starrocks connector。需要开发脚本，每日定时组织同步脚本，重新启动Flink cdc同步程序。

诸如Spark程序开发等工作，属于业务开发，不放入此次实现中。

每次访问时，请求处理流程如下：
![image](https://user-images.githubusercontent.com/4351491/195276763-3b22f007-1155-49e5-b0d7-eb49f913b29b.png)

