package pl.britenet.campuslu.command;

import pl.britenet.campuslu.Constants;
import pl.britenet.campuslu.object.Product;
import pl.britenet.campuslu.service.ProductService;

import java.util.List;

public class GetProductsCommand extends Command {
    private final ProductService productService;

    public GetProductsCommand(ProductService productService) {
        super(Constants.COMMAND_GET_PRODUCTS);
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Product> productList = this.productService.getProducts();
        productList.forEach( product -> {
            System.out.println(product.getName());
        } );
    }
}
