package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void addSupplier(Supplier supplier) {
        validateSupplier(supplier);
        supplierRepository.save(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        validateSupplier(supplier);
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer id) {
        if (supplierRepository.getSupplier(id) == null) {
            throw new IllegalArgumentException("The supply does not exist");
        }
        supplierRepository.delete(id);
    }

    public Supplier getSupplier(Integer id) {

        return supplierRepository.getSupplier(id);
    }

    public boolean existSupplier(int id) {

        return supplierRepository.getSupplier(id) != null;
    }

    private void validateSupplier(Supplier supplier) {
        if (supplier.getPhone() != null && (supplier.getPhone().length() != 10 || !supplier.getPhone().matches("\\d+"))) {
            throw new IllegalArgumentException("Phone must contain 10 digits.");
        }
        String name = supplier.getName();
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

}
