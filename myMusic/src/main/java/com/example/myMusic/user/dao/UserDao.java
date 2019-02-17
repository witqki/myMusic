package com.example.myMusic.user.dao;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.example.myMusic.user.entities.User;
/**
 * Spring Data JPA 常用接口
 * =======================data repository interface===========================
 * Repository：jpa根接口，没有任何方法，只用作标志持久层。(只做自定义查询)
 * CrudRepository:extends Repository接口，提供基本的CRUD数据库操作
			*<S extends T> S save(S entity);		//id
			<S extends T> Iterable<S> saveAll(Iterable<S> entities);
			*Optional<T> findById(ID id);
			*boolean existsById(ID id);
			Iterable<T> findAll();
			Iterable<T> findAllById(Iterable<ID> ids);
			*long count();
			*void deleteById(ID id);
			void delete(T entity);
			void deleteAll(Iterable<? extends T> entities);
			void deleteAll();
 * *PagingAndSortingRepository：extends CrudRepository，提供额外的分页查询及排序功能
 *			Iterable<T> findAll(Sort sort); 
			* Page<T> findAll(Pageable pageable);
 * JpaRepository extends PagingAndSortingRepository，提供额外的 flush以及Batch操作方法。
 * *JpaSpecificationExecutor ：自定义高级（动态条件组装）查询接口
	 		Optional<T> findOne(@Nullable Specification<T> spec);
			List<T> findAll(@Nullable Specification<T> spec);
			* Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
			List<T> findAll(@Nullable Specification<T> spec, Sort sort);
			long count(@Nullable Specification<T> spec);
*======================================data domain=================================
	Page:分页对象：封装了分页的：结果集合、分页条件、排序条件、一共多少条记录、当前页多少条记录...
	Pageable:分页条件对象。第几页、每页多少条记录、排序对象Sort（条件）
	Sort：排序条件对象。排序字段、升降序
	Specification：动态查询条件封装对象。
 */
public interface UserDao extends PagingAndSortingRepository<User, Long>//分页和排序
                                 ,JpaSpecificationExecutor<User>//动态查询
{ 
	@Query("from User u where u.name = ?1")
	User findByUsername(String username);
	@Query("from User u where u.phone = ?1")
	User findByUserphone(String userphone);
	@Query("from User u where u.email = ?1")
	User findByUseremail(String useremail);
      
      
      
}
