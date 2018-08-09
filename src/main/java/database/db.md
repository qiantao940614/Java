# 什么是jdbc
	JDBC是sun开发的一套数据库访问编程接口，是一种SQL级的API。

# jdbc使用了那些接口
	Connection,
	
# 配置jdbc的步骤
 - 1.加载JDBC驱动程序
 - 2.创建数据库的连接   
 - 3.创建一个Statement类()
 	Statement(执行静态sql)
 	PreparedStatement(执行动态sql)
 	boolean execute(String sql)//执行存储过程   结果在(getUpdateCount,getResultSet) 
 	boolean executeUpdate(String sql)//
 	boolean executeQuery(String sql)//
 	
 - 4.执行SQL语句 
 - 5.处理结果
 - 6.关闭连接
 
 
# sql分类:
1. 数据查询语言(DQL)Select语言
2. 数据操作语言(DML)：Insert,Update,delete语句
3. 事务处理语言(TPL)：TPL语句包括BEGIN TRANSACTION，COMMIT和ROLLBACK。
4. 数据控制语言（DCL)
5. 数据定义语言（DDL）：
6. 指针控制语言（CCL）
 
# sql的四大特性 
 原子性:对事务的操作要么成功，要么失败回滚。
一致性:事务操作前后其状态始终一致。
隔离性:事务操作过程中无法被其他事务并发执行
持久性:一旦提交，永久改变

# 不考虑隔离性的三个可能会产生的错误
脏读		 	脏读就是读到了别的事务回滚前的脏数据。
不可重复读 	不可重复读就是读取到数据修改前后两个不同的数据
虚度(幻读)   幻读就是新增加的数据未能得到正常的修改  (一个事务做update操作，一个事务做insert操作或delete操作)

# 数据库的事务隔离级别 
Read Uncommitted(未提交读)：最低的一种隔离级别
Read Committed(提交读):默认事务等级,避免脏读
Repeatable Read(重复读):避免脏读,不可重复,    不能避免幻读
Serializable(串行化):可避免脏读,不可重复读，幻读     最严格的级别资源消耗最大

# Spring事务隔离级别
isolation_default：默认  使用数据库的隔离级别
isolation_read_uncommitted(未提交读)：同数据库的事物Read Uncommitted
isolation_read_committed(提交读):同数据库的事物Read Committed
isolation_repeatable_read:同数据库的事物Repeatable Read
isolation_serializable (串行化):同数据库的事物Serializable

# Spring如何配置hibernate 事务
```
<tx:annotation-driven transaction-manager="transactionManager"/>
 <!-- 定义事务管理器（声明式的事务） --> 
 <bean id="transactionManager"
       class="org.springframework.orm.hibernate3.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory" />
 </bean>
 <bean id="sessionFactory" 
      class="org.springframework.orm.hibernate3.LocalSessionFactoryBean"> 
     <property name="configLocation" value="classpath:hibernate.cfg.xml" /> 
      <property name="configurationClass" value="org.hibernate.cfg.AnnotationConfiguration" />
  </bean> 
```
```
@Transactional
@Component("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao
```

# [*]Spring的传播级别
 
