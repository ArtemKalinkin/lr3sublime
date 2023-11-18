import java.util.ArrayList;
import java.util.List;


public class City {
    private String name;
    private int population;
    private int numberOfEnterprises;
    private List<Enterprise> listOfEnterprises = new ArrayList<>();

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, int population, int numberOfEnterprises, List<Enterprise> listOfEnterprises) {
        this.name = name;
        this.population = population;
        this.numberOfEnterprises = numberOfEnterprises;
        this.listOfEnterprises = listOfEnterprises;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setNumberOfEnterprises(int numberOfEnterprises) {
        this.numberOfEnterprises = numberOfEnterprises;
    }

    public int getNumberOfEnterprises() {
        return numberOfEnterprises;
    }

    public void setListOfEnterprises(List<Enterprise> listOfEnterprises) {
        this.listOfEnterprises = listOfEnterprises;
    }

    public List<Enterprise> getListOfEnterprises() {
        return listOfEnterprises;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof City))
            return false;
        City city = (City) object;
        return this.name.equals(city.getName());
    }


    public void input(List<City> cityList) {
        boolean flag;
        System.out.println("\nВВОД ГОРОДА");
        do {
            flag = false;
            name = AuxiliaryClass.inputNameOfSomething("города");
            for (City otherCity : cityList)
                if ((this != otherCity) && (this.equals(otherCity))) {
                    System.out.println("Данный город уже есть в списке");
                    flag = true;
                }
        } while (flag);

        population = AuxiliaryClass.inputPopulationOfSomething("города");
        numberOfEnterprises = AuxiliaryClass.inputNumberOfSomething("предприятий");

        do {
            Enterprise enterprise = new Enterprise();
            enterprise.input(listOfEnterprises);
            listOfEnterprises.add(enterprise);
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить ввод предприятий (y/n):"));
    }

    public static void tableHeader() {
        System.out.print("**************************************************" +
                "********************************************************\n");
        System.out.print("* Номер *       Город        * Количество предприятий " +
                "* Население *          Список предприятий          *\n");
        System.out.print("**************************************************" +
                "********************************************************\n");
    }

    public void output(int number) {
        System.out.printf("* %-5d * %-18s * %-22d * %-9d * ", number + 1, name, numberOfEnterprises, population);
        if (listOfEnterprises.isEmpty())
            System.out.printf("%-36s *\n", AuxiliaryClass.listIsEmpty);
        else
            System.out.printf("%-36s *\n", listOfEnterprises.get(0).getName());
        for (int i = 1; i < listOfEnterprises.size(); i++) {
            System.out.printf("*       *                    *                        *           * %-36s *\n",
                    listOfEnterprises.get(i).getName());
        }
        System.out.print("*******************************************************" +
                "***************************************************\n");
    }

    public int chooseEnterprise() {
        int number = 0;
        int i;
        int size = listOfEnterprises.size();
        if (size != 0) {
            Enterprise.tableHeader();
            i = 0;
            for (Enterprise enterprise : listOfEnterprises) {
                enterprise.output(i);
                i++;
            }
            do {
                System.out.print("Введите номер предприятия: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > size))
                    System.out.println("Предприятия под данным номером нет в списке");
            } while ((number < 1) || (number > size));
            Main.scanner.nextLine();
        }
        return number - 1;
    }

    public void changeFields(List<City> cityList) {
        boolean flag;
        int number;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        output(0);
        do {
            System.out.println("1.Название города");
            System.out.println("2.Количество предприятий");
            System.out.println("3.Население города");
            System.out.println("4.Список предприятий");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 4))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 4));
            Main.scanner.nextLine();
            switch (number) {
                case 1:
                    do {
                        flag = false;
                        name = AuxiliaryClass.inputNameOfSomething("города");
                        for (City otherCity : cityList)
                            if ((this != otherCity) && (this.equals(otherCity))) {
                                System.out.println("Данный город уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                    break;
                case 2:
                    numberOfEnterprises = AuxiliaryClass.inputNumberOfSomething("предприятий");
                    break;
                case 3:
                    population = AuxiliaryClass.inputPopulationOfSomething("города");
                    break;
                case 4:
                    System.out.println("Для изменения списка предприятий перейдите по соответствующей команде в меню");
                    break;
                default:
                    break;
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном городе?"));
    }

    public void addNewEnterprise() {
        int number;
        if (listOfEnterprises.size() < numberOfEnterprises) {
            Enterprise enterprise = new Enterprise();
            enterprise.input(listOfEnterprises);
            listOfEnterprises.add(enterprise);
        } else {
            System.out.println("Достигнуто количество предприятий соответствующее введенному числу - "
                    + numberOfEnterprises);
            System.out.println("Для добавления новых предприятий в данный список вам необходимо изменить ");
            System.out.println("число количества предприятий в данном субъекте");
            if (AuxiliaryClass.answerYesOrNo("Желаете это сделать?")) {
                do {
                    number = AuxiliaryClass.inputNumberOfSomething("предприятий");
                    if (number <= listOfEnterprises.size())
                        System.out.println("Данное число меньше или соответствует уже имеющемуся");
                } while (number <= listOfEnterprises.size());
                numberOfEnterprises = number;
                addNewEnterprise();
            }
        }
    }

    public void removeEnterpriseFromList() {
        int number;
        number = chooseEnterprise();
        Enterprise.tableHeader();
        listOfEnterprises.get(number).output(0);
        if (AuxiliaryClass.answerYesOrNo("Вы действительно желаете удалить данное предприятие из списка?"))
            listOfEnterprises.remove(number);
    }
}
