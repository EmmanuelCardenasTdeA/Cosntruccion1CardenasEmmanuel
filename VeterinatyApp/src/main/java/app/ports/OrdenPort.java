package app.ports;

import java.util.List;

import app.domain.models.Orden;

public interface OrdenPort {
    public void saveOrden(Orden orden)throws Exception;
    public Orden findByOrdenId(long ordenId)throws Exception;
    public List<Orden> getAllOrden()throws Exception;
} 
