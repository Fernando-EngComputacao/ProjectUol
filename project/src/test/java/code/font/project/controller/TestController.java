package code.font.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import code.font.project.controller.service.ProductServiceImpl;

class TestController {

	private ProductController controller;
	
	@Mock
	ProductServiceImpl implement;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() {
		
		MockitoAnnotations.initMocks(this);
		this.controller = new ProductController(implement);
	}
	
	//Get -> list()
//	@Test
//	void testNeedFindProductList() {
//		Mockito.when(controller.list(null)).thenReturn(null);
//		controller.list(null);
//	}
	
	//@Get{id} -> findById()
	@Test
	void testFindByIdProduct() {
		controller.findByid(null);
	}
	
	//Get("/search") -> search
	@Test
	void testSearchProduct() {
		controller.search(null, null, null);
	}
	
	//Post -> create()
	@Test
	void testCreateProduct() {
		controller.creat(null, null);
	}
	
	//Post(/{id}) -> update()
	@Test
	void testUpdateProduct() {
		controller.update(null, null);
	}
	
	//Delete(/{id}) -> delete()
	@Test
	void testDeleteProduct() {
		controller.delete(null);
	}

}