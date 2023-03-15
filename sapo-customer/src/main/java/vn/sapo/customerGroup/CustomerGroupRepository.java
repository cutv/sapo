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
            "g.type as type," +
            "g.cusGrpCode as cusGrpCode," +
            "g.createdAt as createdAt, " +
            "count(c.groupId) as countCus," +
            "g.note as note " +
            "from CustomerGroup as g " +
            "left join Customer as c " +
            "on g.id = c.groupId " +
            "group by g.id")
            List<ICustomerGroupResult> sortByGroup();

    boolean existsByCusGrpCode(String code);

    boolean existsByTitle(String title);

    @Query(value = "SELECT " +
                        "g.id, " +
                        "g.title, " +
                        "g.cus_grp_code as cusGrpCode, " +
                        "g.type , " +
                        "(SELECT COUNT(customer_group_id) " +
                            "FROM customer " +
                            "WHERE customer_group_id = g.id) AS countCus, " +
                        "g.note, " +
                        "g.created_at AS createdAt " +
                    "FROM customer_group AS g;"
            ,
            nativeQuery = true)
    List<ICustomerGroupResult> findAllCustomerGroupResult();
}




