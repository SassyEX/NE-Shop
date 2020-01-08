package Controller;

import Entity.product;
import Entity.user;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "admin")
@SessionScoped
public class AdminController {

    public String iframe = "blank.xhtml";
    private List<user> users;
    private List<product> products;
    EntityManager em;
    EntityManagerFactory emf;

    private void doConnection(){
        emf = Persistence.createEntityManagerFactory("neshopPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void getUser() {
        doConnection();
        users = em.createNamedQuery("user.findAll", user.class).getResultList();
    }
    
    public void getProduct(){
        UserController u = new UserController();
        u.validateAdmin();
        RequestContext  req = RequestContext.getCurrentInstance();
        doConnection();
        products = em.createNamedQuery("product.findAll", product.class).getResultList();
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }

    public List<user> getUsers() {
        return users;
    }

    public void setUsers(List<user> users) {
        this.users = users;
    }

    public void reDirect(String url) {
        iframe = url;
    }

    public String getIframe() {
        return iframe;
    }

    public void setIframe(String iframe) {
        this.iframe = iframe;
    }

}
