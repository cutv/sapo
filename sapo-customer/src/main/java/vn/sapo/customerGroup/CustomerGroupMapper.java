package vn.sapo.customerGroup;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCustomerGroupParam;
import vn.sapo.entities.customer.CustomerGroup;


@Component
public class CustomerGroupMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerGroup toModel(CreateCusGroupParam createCusGroupParam) {
        return modelMapper.map(createCusGroupParam, CustomerGroup.class);
    }

    public CustomerGroupResult toDTO(CustomerGroup customerGroup) {
        return modelMapper.map(customerGroup, CustomerGroupResult.class);
    }

    public void transferFields(UpdateCustomerGroupParam updateCustomerGroupParam, CustomerGroup customerGroup) {
        modelMapper.map(updateCustomerGroupParam, customerGroup);
    }
}
