package br.com.tisemcensura.fullstack.exceptions;

public class ResourceFindByIdNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceFindByIdNotFoundException(Object id) {
        super( "Resource Not found with id = " + id);
    }
}
