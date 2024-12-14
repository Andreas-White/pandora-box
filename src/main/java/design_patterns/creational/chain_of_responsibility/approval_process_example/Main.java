package design_patterns.creational.chain_of_responsibility.approval_process_example;

public class Main {

    public static void main(String[] args) {
        Director director = new Director();
        VP vp = new VP();
        CEO ceo = new CEO();

        director.setSuccessor(vp);
        vp.setSuccessor(ceo);

        Request request = new Request(RequestType.CONFERENCE, 500);
        director.handleRequest(request);
        System.out.println(request.isApproved());

        request = new Request(RequestType.PURCHASE, 1400);
        director.handleRequest(request);
        System.out.println(request.isApproved());

        request = new Request(RequestType.PURCHASE, 1500);
        director.handleRequest(request);
        System.out.println(request.isApproved());
    }
}
