package pl.pawww.hurt.servlets.orders;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import pl.pawww.hurt.jpa.Orders;

public class doXML {

    static public void zrobXML(Orders order) throws JAXBException {
        JAXBContext jaxbc;
        Marshaller m;
        jaxbc = JAXBContext.newInstance(Orders.class);
        m = jaxbc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(order, new File("/home/r/Pulpit/Zamówienie_" + order.getId() + ".xml"));
    }
}
