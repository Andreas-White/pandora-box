package design_patterns.creational.chain_of_responsibility.approval_process_example;

public record Request(RequestType requestType, double amount) {

    private static boolean approval;
    public void approve() {
        approval = true;
    }

    public boolean isApproved() {
        return approval;
    }
}
