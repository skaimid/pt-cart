package tech.saltyfish.ptcart.exception;

public class LinkNotFoundException extends Exception {
    public LinkNotFoundException(Long id) {
        super(id.toString());
    }
}
