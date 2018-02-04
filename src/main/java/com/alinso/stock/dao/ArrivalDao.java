package com.alinso.stock.dao;

import com.alinso.stock.entity.Arrival;
import com.alinso.stock.entity.User;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by KHAN on 4.02.2018.
 */
@Repository
public class ArrivalDao extends BaseDAO<Arrival> {
//    TODO: Basedao Dışında kllanılacaklar.
    public ArrivalDao(){
        super.setTheClass(Arrival.class);
    }

    public Integer getArrivalCount(User user){
        if(user==null){
            return 0;
        }

        String hql="select count(arrival) from Arrival arrival where arrival.createUser=:createUser";
        Query query=entityManager.createQuery(hql)
                .setParameter("createUser",user);
        Long count= (Long) query.getSingleResult();

        return count.intValue();
    }
}
