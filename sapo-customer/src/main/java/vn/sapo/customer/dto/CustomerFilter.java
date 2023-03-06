package vn.sapo.customer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CustomerFilter {
    private Integer id;

    private String keyword;

    private String customerCode;

    private List<Integer> groupIds;

    private String gender;

    private Integer dayOfBirthday;

    private Integer monthOfBirthday;

    private List<Integer> employeeIds;

    private List<CustomerStatus> statusList;

    private Date createdFrom;

    private Date createdTo;

    private Integer draw;           //page hiện tại

    private Integer length;            // số lượng trên 1 trang

    private Integer start;             // lấy bắt đầu từ
<<<<<<< HEAD
=======

    @Override
    public String toString() {
        return "CustomerFilter{" +
                "keyword='" + keyword + '\'' +
                ", id=" + id +
                ", customerCode='" + customerCode + '\'' +
                ", groupIds=" + groupIds +
                ", gender='" + gender + '\'' +
                ", dayOfBirthday=" + dayOfBirthday +
                ", monthOfBirthday=" + monthOfBirthday +
                ", employeeIds=" + employeeIds +
                ", statusList=" + statusList +
                ", createdFrom=" + createdFrom +
                ", createdTo=" + createdTo +
                ", draw=" + draw +
                ", length=" + length +
                ", start=" + start +
                '}';
    }
>>>>>>> cf9e56958713b8ee99a298cabdf1ddcf9488ab60
}
