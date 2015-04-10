/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.company.project.dao;

import java.util.*;

import javacommon.base.*;
import javacommon.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.company.project.model.*;
import com.company.project.dao.*;
import com.company.project.service.*;
import com.company.project.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


import static cn.org.rapid_framework.util.ObjectUtils.*;
import org.springframework.stereotype.Repository;

@Repository
public class TuserInfoDao extends BaseHibernateDao<TuserInfo,java.lang.Long>{

	public Class getEntityClass() {
		return TuserInfo.class;
	}
	
	public Page findPage(TuserInfoQuery query) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from TuserInfo t where 1=1 "
			  	+ "/~ and t.username = {username} ~/"
			  	+ "/~ and t.password = {password} ~/"
				+ "/~ and t.birthDate >= {birthDateBegin} ~/"
				+ "/~ and t.birthDate <= {birthDateEnd} ~/"
			  	+ "/~ and t.sex = {sex} ~/"
			  	+ "/~ and t.age = {age} ~/"
				+ "/~ order by [sortColumns] ~/";

        //生成sql2的原因是为了不喜欢使用xsqlbuilder的同学，请修改生成器模板，删除本段的生成
        StringBuilder sql2 = new StringBuilder("select t from TuserInfo t where 1=1 ");
        if(isNotEmpty(query.getUserId())) {
            sql2.append(" and  t.userId = :userId ");
        }
        if(isNotEmpty(query.getUsername())) {
            sql2.append(" and  t.username = :username ");
        }
        if(isNotEmpty(query.getPassword())) {
            sql2.append(" and  t.password = :password ");
        }
        if(isNotEmpty(query.getBirthDateBegin())) {
            sql2.append(" and  t.birthDate >= :birthDateBegin ");
        }
        if(isNotEmpty(query.getBirthDateEnd())) {
            sql2.append(" and  t.birthDate <= :birthDateEnd ");
        }
        if(isNotEmpty(query.getSex())) {
            sql2.append(" and  t.sex = :sex ");
        }
        if(isNotEmpty(query.getAge())) {
            sql2.append(" and  t.age = :age ");
        }
        if(isNotEmpty(query.getSortColumns())) {
            sql2.append(" order by :sortColumns ");
        }	
        
		return pageQuery(sql,query);
	}
	

}
