package design_patterns.creational.chain_of_responsibility.approval_process_example;

public class Director extends Handler{
    @Override
    public void handleRequest(Request request) {
        if (request.requestType() == RequestType.CONFERENCE) {
            System.out.println("Director can approve conferences");
            request.approve();
        }
        else {
            successor.handleRequest(request);
        }
    }
}
