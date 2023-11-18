import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Enterprise {
    private String name;
    private String address;
    private long turnoverPerYear;
    private long netProfit;
    private Date dateOfFoundation;
    private String industry;

    public Enterprise() {
    }

    public Enterprise(String name) {
        this.name = name;
    }

    public Enterprise(String name, String address, long turnoverPerYear, long netProfit, Date date, String industry) {
        this.name = name;
        this.address = address;
        this.turnoverPerYear = turnoverPerYear;
        this.netProfit = netProfit;
        this.dateOfFoundation = date;
        this.industry = industry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTurnoverPerYear(long turnoverPerYear) {
        this.turnoverPerYear = turnoverPerYear;
    }

    public long getTurnoverPerYear() {
        return turnoverPerYear;
    }

    public void setNetProfit(long netProfit) {
        this.netProfit = netProfit;
    }

    public long getNetProfit() {
        return netProfit;
    }

    public void setDateOfFoundation(Date dateOfFoundation) {
        this.dateOfFoundation = dateOfFoundation;
    }

    public Date getDateOfFoundation() {
        return dateOfFoundation;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Enterprise))
            return false;
        Enterprise enterprise = (Enterprise) object;
        return this.name.equals(enterprise.getName());
    }

    public long inputTurnover() {
        long turnover;
        do {
            System.out.print("Введите оборот за год: ");
            while (!Main.scanner.hasNextLong()) {
                System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите оборот за год: ");
                Main.scanner.next();
            }
            turnover = Main.scanner.nextLong();
            if (turnoverPerYear < 0)
                System.out.println("Оборот не может быть отрицательным");
        } while (turnoverPerYear < 0);

        Main.scanner.nextLine();
        return turnover;
    }

    public long inputNetProfit() {
        long profit;
        System.out.print("Введите прибыль предприятия: ");
        while (!Main.scanner.hasNextLong()) {
            System.out.print("Ошибка ввода! Необходимо ввести число!\nВведите прибыль предприятия: ");
            Main.scanner.next();
        }
        profit = Main.scanner.nextLong();

        Main.scanner.nextLine();
        return profit;
    }

    public String inputIndustry() {
        String industry;
        do {
            System.out.print("Введите отрасль предприятия: ");
            while (!Main.scanner.hasNextLine()) {
                System.out.print("Ошибка ввода!\nВведите отрасль предприятия: ");
                Main.scanner.next();
            }
            industry = Main.scanner.nextLine();
            if (industry.isBlank())
                System.out.println("Данное поле не может быть пустым");
        } while (industry.isBlank());

        return industry;
    }

    public void inputDateOfFoundation() {
        boolean flag;
        String stringDate;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        do {
            System.out.print("Введите дату основания предприятия в формате (дд.мм.гггг): ");
            try {
                while (!Main.scanner.hasNextLine()) {
                    System.out.print("Ошибка ввода!\nВведите дату основания: ");
                    Main.scanner.next();
                }
                stringDate = Main.scanner.nextLine();
                dateOfFoundation = format.parse(stringDate);
                flag = true;
            } catch (ParseException e) {
                System.out.println("Ошибка ввода!\nДата введена некорректно!");
                flag = false;
            }
        } while (!flag);

    }


    public void input(List<Enterprise> enterpriseList) {
        boolean flag;
        System.out.println("\nВВОД ПРЕДПРИЯТИЯ");
        do {
            flag = false;
            name = AuxiliaryClass.inputNameOfSomething("предприятия");
            for (Enterprise otherEnterprise : enterpriseList)
                if ((this != otherEnterprise) && (this.equals(otherEnterprise))) {
                    System.out.println("Предприятие с данным названием уже есть в списке");
                    flag = true;
                }
        } while (flag);
        turnoverPerYear = inputTurnover();
        netProfit = inputNetProfit();
        industry = inputIndustry();
        inputDateOfFoundation();
    }

    public static void tableHeader() {
        System.out.print("***************************************************************************************" +
                "**************************************************************************************\n");
        System.out.print("* Номер *       Предприятие       *                  Местоположение                 " +
                "*   Оборот за год   *    Прибыль    *              Отрасль             * Дата основания *\n");
        System.out.print("*******************************************************************************************" +
                "**********************************************************************************\n");
    }

    public void output(int number) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        System.out.printf("* %-5d * %-23s * %-47s * ", number + 1, name, address);
        System.out.printf("%-17d * %-13d * ", turnoverPerYear, netProfit);
        System.out.printf("%-32s * %-14s *\n", industry, format.format(dateOfFoundation));
        System.out.print("****************************************************************************************" +
                "*************************************************************************************\n");
    }

    public void changeFields(List<Enterprise> enterpriseList) {
        int number;
        boolean flag;
        System.out.println("\nИЗМЕНЕНИЯ ПОЛЕЙ");
        tableHeader();
        output(0);
        do {
            System.out.println("1.Название предприятия");
            System.out.println("2.Местоположение предприятия");
            System.out.println("3.Оборот за год");
            System.out.println("4.Прибиль предприятия");
            System.out.println("5.Отрасль предприятия");
            System.out.println("6.Дата основания предприятия");
            do {
                System.out.print("Введите номер поля, который желаете изменить: ");
                number = Main.scanner.nextInt();
                if ((number < 1) || (number > 6))
                    System.out.println("Поля с данным номером нет");
            } while ((number < 1) || (number > 6));
            Main.scanner.nextLine();
            switch (number) {
                case 1:
                    do {
                        flag = false;
                        name = AuxiliaryClass.inputNameOfSomething("предприятия");
                        for (Enterprise otherEnterprise : enterpriseList)
                            if ((this != otherEnterprise) && (this.equals(otherEnterprise))) {
                                System.out.println("Данный город уже есть в списке");
                                flag = true;
                            }
                    } while (flag);
                    break;
                case 2:
                    System.out.println("Данное поле заполняется автоматически и его нельзя изменить");
                    System.out.println("Для его изменения вам необходимо совершить корректировку в стране, субъекте");
                    System.out.println("и городе, которые относятся к данному предприятию в полях \"Название\"");
                    break;
                case 3:
                    turnoverPerYear = inputTurnover();
                    break;
                case 4:
                    netProfit = inputNetProfit();
                    break;
                case 5:
                    industry = inputIndustry();
                    break;
                case 6:
                    inputDateOfFoundation();
                    break;
                default:
                    break;
            }
        } while (AuxiliaryClass.answerYesOrNo("Желаете продолжить изменение полей в данном предприятии?"));
    }


}
