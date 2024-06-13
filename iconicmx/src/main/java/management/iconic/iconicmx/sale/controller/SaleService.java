package management.iconic.iconicmx.sale.controller;

import management.iconic.iconicmx.sale.model.Sale;
import management.iconic.iconicmx.sale.model.SaleRepository;
import management.iconic.iconicmx.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class SaleService {
    @Autowired
    SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("OK", false, saleRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if (saleRepository.existsById(id)){
            return new ResponseEntity<>(new Message("OK", false, saleRepository.findById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El producto no existe", true, null), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Sale sale){
        Optional<Sale> existsProduct = saleRepository.findByDescription(sale.getDescription());
        if (existsProduct.isPresent()){
            return new ResponseEntity<>(new Message("El producto ya existe", true, sale), HttpStatus.BAD_REQUEST);
        }
        sale.setAmount(calculateAmount(sale));
        Sale saveSale = saleRepository.saveAndFlush(sale);
        return new ResponseEntity<>(new Message("Producto agregado", false, saveSale), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Sale sale){
        if (saleRepository.existsById(sale.getId())){
            sale.setAmount(calculateAmount(sale));
            Sale updatedSale = saleRepository.saveAndFlush(sale);
            return  new ResponseEntity<>(new Message("Producto actualizado", false, updatedSale), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El producto no existe", true, sale), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return new ResponseEntity<>(new Message("Producto eliminado", false, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("El producto no existe", true, null), HttpStatus.BAD_REQUEST);
    }

    private double calculateAmount(Sale sale) {
        return (sale.getPrice() * sale.getQuantity()) - sale.getDiscount();
    }

}
