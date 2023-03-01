package vn.sapo.customer.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.entities.customer.CustomerGender;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
public class CreateCustomerParam  {
    @NotNull
    private Integer id;

    @NullOrNotBlank
    private String customerCode;

    @NotBlank
    private String fullName;

    @NullOrNotBlank
    private String phoneNumber;

    @NullOrNotBlank
    private String description;

    private Integer groupId;

    @NullOrNotBlank
    private String email;

    @NullOrNotBlank
    private String website;

    @NullOrNotBlank
    private String fax;

    @NullOrNotBlank
    private String taxCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private CustomerGender gender;

    private CustomerGroupResult group;

    private Integer employeeId;

    private CreateAddressParam createAddressParam;

    private BigDecimal debtTotal;

    private BigDecimal spendTotal;

    private CustomerStatus status;
}
