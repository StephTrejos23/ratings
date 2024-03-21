package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Supplier;
import io.javabrains.ratingsdataservice.services.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping("/supplier/{supplierId}")
    public Supplier getSupplier(@PathVariable(value = "supplierId") Integer id) {
        return supplierService.getSupplier(id).orElse(null);
    }

    @PostMapping("/supplier")
    public void createSupplier(@RequestBody Supplier supplier) {
    supplierService.addSupplier(supplier);
    }

    @PutMapping("/supplier/{supplierId}")
    public void updateSupplier(@PathVariable(value = "supplierId") Integer id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/supplier/{supplierId}")
    public void deleteSupplier(@PathVariable(value = "supplierId") Integer id) {
        supplierService.deleteSupplier(id);
    }
}
