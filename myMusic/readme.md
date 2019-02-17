### 一、环境
#### 后端:
1. JDK 版本: 1.8.x
2. maven 版本: 3.x.x
3. mysql 版本: 8.x.x
4. 编程软件: STS-3.9.5.RELEASE(任意)
5. 框架: Spring Boot + Hibernate

#### 前端:
1. 框架: EXT6.x
2. 编辑器: 任意

#### 代码管理
1. 托管平台: http://git.dgut.edu.cn/example.myMusic //假的
2. 安装 git.
2. 常用命令:
```
- clone 项目到本地: git clone 项目的git地址
- 项目提交: 
  本地提交: git add *
            git commit -m "提示信息"
  上传到gitlet: git push origin 分支名
- 项目拉取: git pull origin
```


### 二、约束
1. 数据库名统一为: db_example.myMusic

2. 命名规范:
```
1) 所做模块以 "com.example.myMusic.模块名.*" 命名, * 表示 entities, dao, service, web 层.

2) 接口单独一个包路径, 实现一个包路径.
   例: 接口: com.example.myMusic.student.dao --> 包下类名: StudentDao.java
       实现: com.example.myMusic.student.dao.impl  --> 包下类名: StudentDaoImpl.java 
         
3) java 文件命名规范: 
   dao 层: 
   +  接口: StudentDao.java
   +  实现: StudentDaoImpl.java
   service 层: 
   +  接口: StudentService.java
   +  实现: StudentServiceImpl.java
   web 层: 
   +  StudentController.java
```

3. 所有类(除了测试用例)中, 一致使用日志, 而不要使用 System.out.println(); 进行信息的打印.
```java
/*
 * private Logger logger = LoggerFactory.getLogger(类名.class);
 * logger.info(message);        -> 提示信息
 * logger.error(message, e);    -> 异常信息
 * logger.warn(message);        -> 异常信息
*/
public class StudentDaoImpl implements StudentDao {
	private Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
}
```

### 三、注意事项
1. 提交代码前, 必须要在本地测试通过后再提交.

2. 每次提交代码都必须写好描述信息.

3. 第一次从 gitlet 上 clone 项目后, 首先第一步一定要先创建自己的工作分支, 比如: 
``` 
   以自己的名字拼音命名, 在项目根目录下, 输入如下命令:
    git checkout -b mingzi		# 表示创建 mingzi 分支，然后切换到 mingzi 分支
      
   等价于以下两个命令:   
    git brance mingzi			# 创建 mingzi 分支  
    git checkout mingzi			# 切换到 mingzi 分支  
    
   在本地创建和远程分支对应的分支，使用 git checkout -b branch-name origin/branch-name，本地和远程分支的名称最好一致；
```

4. 每次提交代码如果遇到冲突, 一定要先在群里和小伙伴一起商量再解决冲突, 避免误删其他人的劳动成果(代码).
   
5. 每次实现一个小功能, 可以先在本地上提交:
```
   git add *  
   git commit -m "描述信息"
```
**描述信息最好能简要概述该提交代码片段的内容.**