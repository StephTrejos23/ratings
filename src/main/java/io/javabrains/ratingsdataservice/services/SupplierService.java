package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Invoice;
import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.repository.SupplierRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (!existSupplier(id)) {
            throw new IllegalArgumentException("The supply does not exist");
        }
        supplierRepository.deleteById(id);
    }

    public Optional<Supplier> getSupplier(Integer id) {

        return supplierRepository.findById(id);
    }

    public boolean existSupplier(int id) {
        return getSupplier(id).isPresent();
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
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
