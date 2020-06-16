public class CoffeMachine {
    private int[] drinkPrices = {150, 80, 20, 40};
    private String[] drinkNames = {"капуччино", "эспрессо", "вода", "молоко"};
    private int totalMoneyAmount;

    public CoffeMachine(int moneyAmount){
        totalMoneyAmount = moneyAmount;
    }

    public void checkPrices(){
        for (int i = 0; i < drinkPrices.length; i ++) {
            if(totalMoneyAmount >= drinkPrices[i])
                printInfo(drinkNames[i]);
        }
    }
    private void printInfo(String drinkName){
        System.out.println("Вы можете купить " + drinkName);
    }
}
