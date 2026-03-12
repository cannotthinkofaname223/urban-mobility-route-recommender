# 🏙 Urban Mobility Route Recommender

  
**一个基于 Dijkstra 算法的城市路线推荐 Demo，支持模拟交通拥堵和高峰期路况调整。**

---

##  项目背景

在城市出行中，交通拥堵导致传统最短路径算法不够实用。  
本项目旨在：

- 快速计算起点到终点的最短路径  
- 根据高峰期动态调整路线

---

##  方法概览

graph LR
A[起点 Node A] --> B[路段 AB]
B --> C[路段 BC]
C --> D[路段 CD]
D --> E[终点 Node E]

subgraph 算法流程
F[Dijkstra 初始化] --> G[计算最短路径]
G --> H[应用交通因子]
H --> I[输出路线和预计时间]
end
