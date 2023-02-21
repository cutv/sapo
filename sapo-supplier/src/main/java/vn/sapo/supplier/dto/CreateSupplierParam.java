package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.address.dto.CreateAddressParam;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.security.PrivateKey;

@Getter
@Setter
@Accessors(chain = true)
public class CreateSupplierParam extends AbstractSupplierParam {
    @NullOrNotBlank(message = "supplierCode not blank")
    private String supplierCode;
    private BigDecimal debtTotal;
    private CreateAddressParam createAddressParam;

}