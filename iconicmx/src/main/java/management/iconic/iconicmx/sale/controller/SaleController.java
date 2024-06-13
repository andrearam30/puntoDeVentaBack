package management.iconic.iconicmx.sale.controller;

import management.iconic.iconicmx.sale.model.Sale;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = {"*"})
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping("/")
    public ResponseEntity<Message> getAll() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable long id) {
        return saleService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody SaleDTO saleDTO) {
        return saleService.save(new Sale(saleDTO.getDescription(), saleDTO.getPrice(), saleDTO.getQuantity(), saleDTO.getAmount(), saleDTO.getDiscount()));
    }

    @PutMapping("/")
    public ResponseEntity<Message> update(@RequestBody SaleDTO saleDTO) {
        Sale sale = new Sale(saleDTO.getDescription(), saleDTO.getPrice(), saleDTO.getQuantity(), saleDTO.getAmount(), saleDTO.getDiscount());
        return saleService.update(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable long id) {
        return saleService.delete(id);
    }
}
