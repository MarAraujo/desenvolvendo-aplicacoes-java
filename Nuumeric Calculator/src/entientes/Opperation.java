//=================================================================== Codigo Class Operações ==============================================================
package entientes;

public class Opperation {


        private double number1;
        private double number2;

        private double result;

        public Opperation(){

        }

        public double getNumber1() {
            return number1;
        }

        public double getNumber2() {
            return number2;
        }

        public void setNumber1(double number1) {
            this.number1 = number1;
        }

        public void setNumber2(double number2) {
            this.number2 = number2;
        }

        public void sum(){
            result = number1 + number2;
        }
        public void subtration(){
            result = number1 - number2;
        }
        public void multiplication(){
            result = number1 * number2;
        }
        public void division(){
            if(getNumber2() == 0){
                throw new IllegalArgumentException();
            }else{
                result = number1 / number2;
            }
        }

        public String toString() {
            return "O resultado da Operação é igua a "
                    + String.format("%.2f",result) + "\nPressione 'ENTER' para continuar...";
        }

}
