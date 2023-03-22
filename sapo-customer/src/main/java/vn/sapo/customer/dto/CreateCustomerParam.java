package vn.sapo.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import vn.sapo.customers.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam {
    private Integer id;

    @NotBlank(message = "{customer.validation.fullName.notBlank}")
    @Length(max = 255, message = "{customer.validation.fullName.length}")
    private String fullName;

    @NullOrNotBlank
    @Length(max = 50, message = "{customer.validation.customerCode.length}")
    private String customerCode;

    private CustomerGroupResult group;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "{customer.validation.phoneNumber.pattern}")
    @NullOrNotBlank
    private String phoneNumber;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{customer.validation.email.pattern}")
    @NullOrNotBlank
    private String email;

    private CreateAddressParam createAddressParam;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "{customer.validation.fax.pattern}")
    @NullOrNotBlank
    private String fax;

    @NullOrNotBlank
    @Length(max = 50, message = "{customer.validation.taxCode.length}")
    private String taxCode;

    @NullOrNotBlank
    @Length(max = 255, message = "{customer.validation.website.length}")
    private String website;

//    @Min(value = -999999999, message = "{customer.validation.debtTotal.min}")
//    @Max(value = 999999999, message = "{customer.validation.debtTotal.max}")
    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private Integer employeeId;

    @NullOrNotBlank
    @Length(max = 500, message = "{customer.validation.description.length}")
    private String description;

    private List<String> tags;

    private Integer groupId;

//    private CustomerStatus status;

//    @Override
//    public String toString() {
//        return "CreateCustomerParam{" +
//                "id=" + id +
//                ", customerCode='" + customerCode + '\'' +
//                ", fullName='" + fullName + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", description='" + description + '\'' +
//                ", groupId=" + groupId +
//                ", email='" + email + '\'' +
//                ", website='" + website + '\'' +
//                ", fax='" + fax + '\'' +
//                ", taxCode='" + taxCode + '\'' +
//                ", birthday=" + birthday +
//                ", gender=" + gender +
//                ", group=" + group +
//                ", employeeId=" + employeeId +
//                ", createAddressParam=" + createAddressParam +
//                ", debtTotal=" + debtTotal +
//                ", spendTotal=" + spendTotal +
//                ", status=" + status +
//                '}';
//    }
}
