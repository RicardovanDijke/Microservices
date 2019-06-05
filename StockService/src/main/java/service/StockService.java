package service;

import dao.ProductDAO;
import domain.Product;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Ricardo van Dijke
 */
public class StockService {
 
    @Inject
    private ProductDAO productDAO;
    
    

    public List<Product> getStock() {
        
        return productDAO.getStock();
   }
}
