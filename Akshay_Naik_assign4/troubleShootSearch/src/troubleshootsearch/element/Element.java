package troubleshootsearch.element;

import troubleshootsearch.visitor.VisitorI;

public interface Element{
    public void accept(VisitorI visitorIn);
}