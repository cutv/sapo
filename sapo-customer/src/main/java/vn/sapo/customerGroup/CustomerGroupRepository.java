package vn.sapo.customerGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroupResult;
import vn.sapo.entities.customer.CustomerGroup;

import java.util.List;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer> {
    @Query("select " +
            "g.id as id," +
            "g.title as title," +
            "g.cusGrpCode as cusGrpCode," +
            "g.createdAt as createdAt, " +
            "count(c.groupId) as countCus," +
            "g.note as note " +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
            List<ICustomerGroupResult> sortByGroup();


    //TODO:KO CAN NUA
//    @Query(value = "SELECT cus_grp_code AS cgc " +
//            "FROM customer_group AS cg " +
//            "WHERE cg.cus_grp_code " +
//            "LIKE 'CTN%' " +
//            "ORDER BY cgc " +
//            "DESC LIMIT 1;"
//            ,
//            nativeQuery = true)
//    String getMaxSystemCustomerGroupCode();

    boolean existsByCusGrpCode(String code);

    boolean existsByTitle(String title);

    @Query(value = "SELECT " +
                        "g.id, " +
                        "g.title, " +
                        "g.cus_grp_code, " +
                        "g.cus_grp_type, " +
                        "(SELECT COUNT(customer_group_id) AS countCus " +
                            "FROM customer " +
                            "WHERE customer_group_id = g.id), " +
                        "g.description, " +
                        "g.created_at " +
                    "FROM customer_group AS g;"
            ,
            nativeQuery = true)
    List<CustomerGroupResult> findAllCustomerGroupResult();

//    @Query(value = "SELECT NEW vn.sapo.customerGroup.dto.CustomerGroupResult (" +
//            "g.id, " +
//            "g.title, " +
//            "g.cusGrpCode, " +
//            "g.cusGrpType, " +
//            "g.id, " +
//            "g.description, " +
//            "g.createdAt )" +
//            "FROM CustomerGroup AS g;"
////            ,
////            nativeQuery = true
//    )
//    List<CustomerGroupResult> findAllCustomerGroupResult();
}




