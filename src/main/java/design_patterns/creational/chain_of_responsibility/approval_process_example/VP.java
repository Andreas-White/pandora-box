package design_patterns.creational.chain_of_responsibility.approval_process_example;

public class VP extends Handler{
    @Override
    public void handleRequest(Request request) {
        if (request.requestType() == RequestType.PURCHASE && request.amount() < 1500) {
            System.out.println("VPs can approve purchases below 1500");
            request.approve();
        }
        else {
            successor.handleRequest(request);
        }
    }
}
