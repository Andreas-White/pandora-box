package design_patterns.creational.chain_of_responsibility.approval_process_example;

public class CEO extends Handler{
    @Override
    public void handleRequest(Request request) {
        System.out.println("CEO can approve everything");
        request.approve();
    }
}
