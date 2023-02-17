package vn.sapo.customerGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customerGroup.dto.CreateCusGroupParam;
import vn.sapo.customerGroup.dto.CustomerGroupResult;
import vn.sapo.customerGroup.dto.ICustomerGroupResult;
import vn.sapo.customerGroup.dto.UpdateCustomerGroupParam;
import vn.sapo.entities.customer.CustomerGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Override
    @Transactional
    public CustomerGroupResult create(CreateCusGroupParam createParam) {
        CustomerGroup customerGroup = customerGroupMapper.toModel(createParam);
        customerGroup = customerGroupRepository.save(customerGroup);
        if (customerGroup.getCusGrpCode() == null)
            customerGroup.setCusGrpCode(CodePrefix.CUSTOMER_GROUP.generate(customerGroup.getId()));
        return customerGroupMapper.toDTO(customerGroup);

    }

    @Override
    @Transactional
    public CustomerGroupResult update(UpdateCustomerGroupParam updateParam) {
        CustomerGroup customerGroup = customerGroupRepository.findById(updateParam.getId())
                .orElseThrow(() -> new NotFoundException("Customer Group not found"));
        customerGroupMapper.transferFields(updateParam, customerGroup);
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public List<CustomerGroupResult> findAll() {
        return customerGroupRepository.findAll()
                .stream()
                .map(customerGroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ICustomerGroupResult> sortByGroup() {
        return customerGroupRepository.sortByGroup();
    }

    @Override
    @Transactional
    public CustomerGroupResult findById(Integer id) {
        CustomerGroup customerGroup = customerGroupRepository.findById(id).get();
        return customerGroupMapper.toDTO(customerGroup);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        customerGroupRepository.deleteById(id);
    }
}
