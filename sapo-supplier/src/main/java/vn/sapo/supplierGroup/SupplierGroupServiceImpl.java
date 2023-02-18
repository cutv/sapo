package vn.sapo.supplierGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.supplier.SupplierGroup;
import vn.sapo.shared.configurations.CodePrefix;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.supplierGroup.dto.CreateSupGroupParam;
import vn.sapo.supplierGroup.dto.EditSupGroupParam;
import vn.sapo.supplierGroup.dto.SupplierGroupResult;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierGroupServiceImpl implements SupplierGroupService{
    @Autowired
    SupplierGroupRepository supplierGroupRepository;

    @Autowired
    SupplierGroupMapper supplierGroupMapper;
    @Override
    public SupplierGroupResult create(CreateSupGroupParam createSupGroupParam) {
//        List<SupplierGroup> supplierGroupList = supplierGroupRepository.findAll();
//        System.out.println("Nhóm nhà cung cấp" + supplierGroupList);
//       for(int i = 0; i< supplierGroupList.size(); i++) {
//           if(createSupGroupParam.getTitle().equalsIgnoreCase(supplierGroupList.get(i).getTitle())) {
//               break;
//           }
//       }
        SupplierGroup supplierGroup = supplierGroupMapper.toModel(createSupGroupParam);
        supplierGroupRepository.save(supplierGroup);
        String supplierCode = createSupGroupParam.getSupplierCode();
        if (supplierCode == null)
            supplierGroup.setSupplierCode(CodePrefix.SUPPLIER.generate(supplierGroup.getId()));
        return supplierGroupMapper.toDTO(supplierGroup);
    }

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
                .orElseThrow(() -> new NotFoundException("Not found group supplier with id: " + id));
        return supplierGroupMapper.toDTO(supplierGroup) ;
    }

    @Override
    @Transactional
    public SupplierGroupResult update(EditSupGroupParam editSupGroupParam) {
        SupplierGroup supplierGroup = supplierGroupRepository.findById(editSupGroupParam.getId())
                .orElseThrow(() -> new NotFoundException("Not found group supplier with id: " + editSupGroupParam.getId() ));
        supplierGroupMapper.transferFields(editSupGroupParam, supplierGroup);
        return supplierGroupMapper.toDTO(supplierGroup);
    }

    @Override
    public void deleteById(Integer id) {
        supplierGroupRepository.deleteById(id);
    }
}