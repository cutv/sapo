package vn.sapo.supplierGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.shared.exceptions.ValidationException;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;
import vn.sapo.supplierGroup.dto.UpdateSupGroupParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService {
    @Autowired
    SupplierGroupRepository supplierGroupRepository;

    @Autowired
    SupplierGroupMapper supplierGroupMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SupplierGroupResult> finAll() {
        return supplierGroupRepository
                .findAll()
                .stream()
                .map(supplierGroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierGroupResult findById(Integer id) {
        SupplierGroup supplierGroup = supplierGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("supplier_group.exception.notFound"));
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    public void validationExistsByTitle(String title) {
        if (supplierGroupRepository.existsByTitle(title))
            throw new ValidationException(new HashMap<>() {{
                put("title", "supplier_group.exception.title.existed");
            }});
    }

    public void validationExistsBySupGroupCode(String supGroupCode) {
        if (supGroupCode.startsWith(CodePrefix.SUPPLIER_GROUP.getValue())) {
            throw new ValidationException(new HashMap<>() {{
                put("supGroupCode", "supplier_group.exception.supGroupCode.hasSystemPrefix");
            }});
        }
        if (supplierGroupRepository.existsBySupGroupCode(supGroupCode))
            throw new ValidationException(new HashMap<>() {{
                put("supGroupCode", "supplier_group.exception.supGroupCode.existed");
            }});
    }

    @Override
    @Transactional
    public SupplierGroupResult create(CreateSupGroupParam createParam) {
        validationExistsByTitle(createParam.getTitle());
        validationExistsBySupGroupCode(createParam.getSupGroupCode());

        SupplierGroup supplierGroup = supplierGroupMapper.toModel(createParam);
        supplierGroupRepository.save(supplierGroup);
        if (createParam.getSupGroupCode() == null)
            supplierGroup.setSupGroupCode(CodePrefix.SUPPLIER_GROUP.generate(supplierGroup.getId()));

        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    @Transactional
    public SupplierGroupResult update(UpdateSupGroupParam updateParam) {
        SupplierGroup supplierGroup = supplierGroupRepository.findById(updateParam.getId())
                .orElseThrow(() -> new NotFoundException("supplier_group.exception.notFound"));

        String title = updateParam.getTitle();
        if (!supplierGroup.getTitle().equalsIgnoreCase(title))
            validationExistsByTitle(title);

        String supGroupCode = updateParam.getSupGroupCode();
        if (!supplierGroup.getSupGroupCode().equalsIgnoreCase(supGroupCode))
            validationExistsBySupGroupCode(supGroupCode);

        supplierGroupMapper.transferFields(updateParam, supplierGroup);
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    public void deleteById(Integer id) {
        supplierGroupRepository.deleteById(id);
    }

    @Override
    public Map<String, Integer> findByGroupCodes(Set<String> groupCodes) {
        return supplierGroupRepository.findBySupGroupCodeIn(groupCodes).stream()
                .collect(Collectors.toMap(SupplierGroup::getSupGroupCode, SupplierGroup::getId));
    }
}
