public class Main {

    public static void main(String[] args) {
//        task1();
//        task2();
//        testIndex();


        //please run with testSmall inputs 1, 2 ,3


        testSmall(1);

    }


    public static void task1() {
        try {
            Treap<Integer> temp = new Treap();
//
//            for (int i = 0; i < 10; i++) {
//
                temp.insert(3);
                temp.insert(5);
                temp.insert(-1);
                temp.insert(8);
                temp.insert(2);
//                temp.insert(8);
                temp.insert(1);
                temp.insert(10);
                temp.insert(9);
                temp.insert(7);
//                System.out.println(temp);
//            }



            System.out.println(temp);

            for (int i = 0; i < 20; i++) {
                temp.access(3);
            }
            System.out.println(temp);


            for (int i = 0; i < 1000; i++) {
                temp.access(3);
            }
            System.out.println(temp);


            System.out.println("deleted: 4 :" + temp.remove(4));
            System.out.println(temp);

            System.out.println("deleted: 9 :" + temp.remove(9));
            System.out.println(temp);

            System.out.println("deleted: 25 :" + temp.remove(25));
            System.out.println(temp);

            System.out.println("deleted: 3 :" + temp.remove(3));
            System.out.println(temp);

        } catch (DatabaseException e)
        {
            System.out.println(e.toString());
        }
    }

    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         *
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         */
        String[] columns = {"Module Code", "Description", "Credits", "Year", "Session"};
        Database db = new Database(columns, 100);

        String[][] modules = {
                {"LST110", "Language and study skills 110", "6", "1", "Sem 1"},
                {"WTW124", "Mathematics 124", "16", "1", "Sem 2"},
                {"UP0102", "Academic orientation 102", "0", "1", "Year"},
                {"WTW114", "Calculus 114", "16", "1", "Sem 1"},
                {"WTW123", "Numerical analysis 123", "8", "1", "Sem 2"},
                {"PHY114", "First course in physics 114", "16", "1", "Sem 1"},
                {"PHY124", "First course in physics 124", "16", "1", "Sem 2"},
                {"AIM102", "Academic information management 102", "6", "1", "Sem 2"},
                {"COS122", "Operating systems 122", "16", "1", "Sem 2"},
                {"COS132", "Imperative programming 132", "16", "1", "Sem 1"},
                {"COS110", "Program design: Introduction 110", "16", "1", "Sem 2"},
                {"COS151", "Introduction to computer science 151", "8", "1", "Sem 1"},
                {"COS212", "Data structures and algorithms 212", "16", "2", "Sem 1"},
                {"COS226", "Concurrent systems 226", "16", "2", "Sem 2"},
                {"COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2"},
                {"COS210", "Theoretical computer science 210", "8", "2", "Sem 1"},
                {"WTW248", "Vector analysis 248", "12", "2", "Sem 2"},
                {"PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1"},
                {"PHY263", "General physics 263", "24", "2", "Sem 2"},
                {"WTW211", "Linear algebra 211", "12", "2", "Sem 1"},
                {"WTW218", "Calculus 218", "12", "2", "Sem 1"},
                {"WTW220", "Analysis 220", "12", "2", "Sem 2"},
                {"COS314", "Artificial intelligence 314", "18", "3", "Sem 1"},
                {"COS330", "Computer security and ethics 330", "18", "3", "Sem 2"},
                {"COS333", "Programming languages 333", "18", "3", "Sem 2"},
                {"COS344", "Computer graphics 344", "18", "3", "Sem 1"},
                {"PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2"},
                {"PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1"},
                {"PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2"},
                {"COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2"},
                {"FSK700", "Physics 700", "135", "4", "Year"}
        };

        try {
            for (String[] mod : modules) {
                db.insert(mod);
            }

            db.generateIndexAll();
        } catch (DatabaseException e) {
            System.out.println("Error: " + e);
        }

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }

        System.out.println(db.indexes[0]);
        System.out.println(db.indexes[1]);

        System.out.println("------------------");
        System.out.println("Custom tests");
        System.out.println("------------------");


        for (int i = 0; i < db.indexes.length; i++) {
            System.out.println(db.indexes[i]);
        }
        System.out.println("------------------");
        System.out.println("deletes");
        System.out.println("------------------");

        //test deleteFirst
        try {
            String[] temp = db.removeFirstWhere("Credits", "6");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }

//        test deleteAllWhere

        String[] column1 = {
                "LST110",
                "WTW124",
                "UP0102",
                "WTW114",
                "WTW123",
                "PHY114",
                "PHY124",
                "AIM102",
                "COS122",
                "COS132",
                "COS110",
                "COS151",
                "COS212",
                "COS226",
                "COS284",
                "COS210",
                "WTW248",
                "PHY255",
                "PHY263",
                "WTW211",
                "WTW218",
                "WTW220",
                "COS314",
                "COS330",
                "COS333",
                "COS344",
                "PHY310",
                "PHY356",
                "PHY364",
                "COS711",
                "FSK700"
        };
        for (int i = 0; i < column1.length; i++) {


            try {
                String[][] temp = db.removeAllWhere("Module Code", column1[i]);

                for (String[] row : temp) {
                    if (row[0] != null) {
                        int c = 0;
                        for (String s : row) {
                            if (c++ == 1) {
                                System.out.print(String.format("%1$-75s", s));
                            } else {
                                System.out.print(s + "\t");
                            }
                        }
                        System.out.println();
                    }

                }

            } catch (DatabaseException e) {
                System.out.println(e);
            }

            for (int j = 0; j < db.indexes.length; j++) {
                if (db.indexes[j] != null) {
                    System.out.println("valid treap:" + validTreap(db.indexes[j]));
                }
                System.out.println(db.indexes[j]);
            }
        }
        System.out.println("find");

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("------------------");
        System.out.println("find");
        System.out.println("------------------");

//        test findFirst
        try {
            String[] temp = db.findFirstWhere("Credits", "16");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }

        //test findAllWhere
        try {
            String[][] temp = db.findAllWhere("Credits", "16");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        System.out.println("updates");

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("------------------");
        System.out.println("update");
        System.out.println("------------------");

        //test updateFirst
        try {
            String[] temp = db.updateFirstWhere("Credits", "16", "6");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }

        //test updateAllWhere
        try {
            String[][] temp = db.updateAllWhere("Credits", "16", "6");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
            {
                System.out.println(e);
            }



        }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }


    public static void testIndex()
    {
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        Database db = new Database(columns, 100);

        String[][] modules = {
                { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
                { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
                { "UP0102", "Academic orientation 102", "0", "1", "Year" },
                { "WTW114", "Calculus 114", "16", "1", "Sem 1" }};

        try {
            for (String[] mod : modules) {
                db.insert(mod);
            }

//            db.generateIndexOn("Module Code");
            db.generateIndexAll();

            for (String[] row : db.database) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }
            }

            for (int i = 0; i < db.indexes.length; i++) {
                System.out.println(db.indexes[i]);
            }


        } catch (DatabaseException e) {
            System.out.println("Error: " + e);
        }



    }


    public static void testSmall(int num)
    {
        String[] columns = {"Module Code", "Description", "Credits", "Year", "Session"};
        Database db = new Database(columns, 100);

        String[][] modules = {
                {"LST110", "Language and study skills 110", "6", "1", "Sem 1"},
                {"WTW124", "Mathematics 124", "16", "1", "Sem 2"},
                {"UPO102", "Academic orientation 102", "0", "1", "Year"},
                {"WTW114", "Calculus 114", "16", "1", "Sem 1"},
                {"WTW123", "Numerical analysis 123", "8", "1", "Sem 2"},
                {"PHY114", "First course in physics 114", "16", "1", "Sem 1"}};

        try {
            for (String[] mod : modules) {
                db.insert(mod);
            }

            db.generateIndexAll();
        } catch (DatabaseException e) {
            System.out.println("Error: " + e);
        }

        print(db);

        switch (num)
        {
            case 1:

                deleteTest(db);
                deleteAllTest(db);
                updateTest(db);
                updateAllTest(db);
                findTest(db);
                findAllTest(db);

                break;
            case 2:

                updateAllTest(db);
                findAllTest(db);
                deleteAllTest(db);
                updateTest(db);
                deleteTest(db);
                findTest(db);

                break;
            case 3:

                findAllTest(db);
                findTest(db);
                updateTest(db);
                updateAllTest(db);
                deleteTest(db);
                deleteAllTest(db);

                break;
            default:
        }

//
//
//
//
//
//
//
//

    }

    public static void emptyTest()
    {
        String[] columns = {"Module Code", "Description", "Credits", "Year", "Session"};
        Database db = new Database(columns, 100);
    }


    public static void print(Database db)
    {
        System.out.println("<____________________Database____________________>");
        for (String[] row : db.database) {
            if (row[0] != null) {
                int c = 0;
                for (String s : row) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("<_____________________Treap______________________>");
        for (int i = 0; i < db.indexes.length; i++) {
            if(db.indexes[i] != null)
            {
                System.out.println("\nindex at:" + i + "\t"  +validTreap(db.indexes[i]) + "\n");
            }
            else
            {
                System.out.println("\nindex at:" + i + "\t"  +"null" + "\n");
            }
            System.out.println(db.indexes[i]);
        }
        System.out.println("<________________________________________________>");

    }

    public static void deleteTest(Database db)
    {
        System.out.println("--------------------------");
        System.out.println("Delete first");
        System.out.println("--------------------------");
        System.out.println("valid delete:" + "UPO102");

        try {
            String[] temp = db.removeFirstWhere("Module Code", "UPO102");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid delete:" + "UPO102");

        try {
            String[] temp = db.removeFirstWhere("Module Code", "UPO102");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid delete:" + "mod col");

        try {
            String[] temp = db.removeFirstWhere("Module", "UPO102");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes delete:" + "6 credits");

        try {
            String[] temp = db.removeFirstWhere("Credits", "6");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes delete:" + "PHY114");

        try {
            String[] temp = db.removeFirstWhere("Module Code", "PHY114");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

    }

    public static void deleteAllTest(Database db)
    {

        System.out.println("--------------------------");
        System.out.println("Delete all");
        System.out.println("--------------------------");
        System.out.println("valid delete:" + "6");
        try {
            String[][] temp = db.removeAllWhere("Credits", "16");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }
            print(db);

        } catch (DatabaseException e) {
            System.out.println(e);
        }




        String[] column1 = {
                "LST110",
                "WTW124",
                "UPO102",
                "WTW114",
                "WTW123",
                "PHY114",
                "PHY124",
                "AIM102",
                "COS122",
                "COS132",
                "COS110",
                "COS151",
                "COS212",
                "COS226",
                "COS284",
                "COS210",
                "WTW248",
                "PHY255",
                "PHY263",
                "WTW211",
                "WTW218",
                "WTW220",
                "COS314",
                "COS330",
                "COS333",
                "COS344",
                "PHY310",
                "PHY356",
                "PHY364",
                "COS711",
                "FSK700"
        };
        for (int i = 0; i < column1.length; i++) {


            try {
                System.out.println("valid delete:" + column1[i]);

                String[][] temp = db.removeAllWhere("Module Code", column1[i]);

                for (String[] row : temp) {
                    if (row[0] != null) {
                        int c = 0;
                        for (String s : row) {
                            if (c++ == 1) {
                                System.out.print(String.format("%1$-75s", s));
                            } else {
                                System.out.print(s + "\t");
                            }
                        }
                        System.out.println();
                    }

                }
                print(db);

            } catch (DatabaseException e) {
                System.out.println(e);
            }
        }
    }
    public static void updateTest(Database db)
    {
        System.out.println("--------------------------");
        System.out.println("update first");
        System.out.println("--------------------------");

        System.out.println("valid update:" + "6->16");

        try {
            String[] temp = db.updateFirstWhere("Credits", "6", "16");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);

        System.out.println("valid update:" + "UPO102->Useless Shit");

        try {
            String[] temp = db.updateFirstWhere("Module Code", "UPO102", "Useless Shit");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);


        System.out.println("invalid udpate:" + "mod col");

        try {
            String[] temp = db.updateFirstWhere("Modul", "UPO102", "Useless Shit");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);

        System.out.println("invalid update:" + "Sem 120 -> Sem 2");
        try {
            String[] temp = db.updateFirstWhere("Session", "Sem120", "Sem 2");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);


        System.out.println("valid update:" + "PHY114 -> Annoying shit");
        try {
            String[] temp = db.updateFirstWhere("Module Code", "PHY114", "Annoying shit");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);



        System.out.println("extreme update:" + "1 -> 5");
        try {
            String[] temp = db.updateFirstWhere("Year", "1", "5");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);


        System.out.println("extreme update:" + "1 -> 5");
        try {
            String[] temp = db.updateFirstWhere("Year", "1", "5");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }

        print(db);

    }
    public static void updateAllTest(Database db)
    {
        System.out.println("--------------------------");
        System.out.println("update all");
        System.out.println("--------------------------");

        System.out.println("valid update:" + "Year = 2");

        try {
            String[][] temp = db.updateAllWhere("Year", "1", "2");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);

        System.out.println("valid update:" + "UPO102 = shit");

        try {
            String[][] temp = db.updateAllWhere("Module Code", "UPO102", "shit");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);

        System.out.println("valid update:" + "UPO = shit");
        try {
            String[][] temp = db.updateAllWhere("Module Code", "UPO102", "shit");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);


        System.out.println("invalid update:" + "1 = 0");
        try {
            String[][] temp = db.updateAllWhere("Module Code", "0", "1");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);
        System.out.println("invalid update:" + "mod col");
        try {
            String[][] temp = db.updateAllWhere("Mo Code", "0", "1");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);


        System.out.println("extremes update: " +  "session 1 = 3");
        try {
            String[][] temp = db.updateAllWhere("Session", "Sem 1", "Sem X");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

//            }

            }
        }catch(DatabaseException e)
        {
            System.out.println(e);
        }
        print(db);


    }

    public static void findTest(Database db)
    {
        System.out.println("--------------------------");
        System.out.println("find first");
        System.out.println("--------------------------");




        System.out.println("valid find:" + "Credits 16");
        try {
            String[] temp = db.findFirstWhere("Credits", "16");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("valid find: " + "UPO102");
        try {
            String[] temp = db.findFirstWhere("Module Code", "UPO102");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid find:");

        try {
            String[] temp = db.findFirstWhere("Mo Code", "UPO102");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid find:");

        try {
            String[] temp = db.findFirstWhere("Module Code", "U02");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes find: year 1");

        try {
            String[] temp = db.findFirstWhere("Year", "1");

            if (temp != null) {
                int c = 0;
                for (String s : temp) {
                    if (c++ == 1) {
                        System.out.print(String.format("%1$-75s", s));
                    } else {
                        System.out.print(s + "\t");
                    }
                }
                System.out.println();
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes find: " + "");

        try {

            for (int i = 0; i < 1000; i++) {
                String[] temp = db.findFirstWhere("Module Code", "UPO102");

                if (temp != null) {
                    int c = 0;
                    for (String s : temp) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);
    }
    public static void findAllTest(Database db)
    {

        System.out.println("--------------------------");
        System.out.println("find all");
        System.out.println("--------------------------");

        System.out.println("valid find:" + "credits 16");

        try {
            String[][] temp = db.findAllWhere("Credits", "16");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("valid find:" + "Year = 1");

        try {
            String[][] temp = db.findAllWhere("Year", "1");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("valid find:" + "PHY114");

        try {
            String[][] temp = db.findAllWhere("Module Code", "PHY114");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid find:" + "jbafkjdafs");

        try {
            String[][] temp = db.findAllWhere("Session", "kjadkhsafdk");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("invalid find:" + "mod col");

        try {
            String[][] temp = db.findAllWhere("ndfsjk", "kjadkhsafdk");

            for (String[] row : temp) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }

            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes find:" + "WTW114");


        try {
            for (int i = 0; i < 500; i++) {

                String[][] temp = db.findAllWhere("Module Code", "WTW114");

                for (String[] row : temp) {
                    if (row[0] != null) {
                        int c = 0;
                        for (String s : row) {
                            if (c++ == 1) {
                                System.out.print(String.format("%1$-75s", s));
                            } else {
                                System.out.print(s + "\t");
                            }
                        }
                        System.out.println();
                    }

                }
            }

        } catch (DatabaseException e) {
            System.out.println(e);
        }
        print(db);

        System.out.println("extremes find:");
    }


}

/*
public class Main {
    
    public static void main(String[] args) {
        //task1();
        //task2();
        //testTreapIns();
        //testTreapRem();
        testTreapAccess();
    }
    public static void testTreapAccess(){
        Treap<String> treap = new Treap<String>();
        String[] strAdd = {"A", "Z", "F", "M", "S", "D", "P", "X", "N", "E"};
        String[] strRem = { "X", "M", "E", "Z", "F", "N", "A", "S", "D", "P", "E"};
        try {
            System.out.println(treap.toString());
            for (String string : strAdd) {
                treap.insert(string);
            }
            System.out.println(treap.toString());
            System.out.println("##############################################################");
            treap.access("M");
            System.out.println(treap.toString());
            treap.access("F");
            System.out.println(treap.toString());
            
        } catch (Exception e) {
            System.out.println("Database Exception");
            e.printStackTrace();
        }
    }
    public static void testTreapRem(){
        Treap<String> treap = new Treap<String>();
        String[] strAdd = {"A", "Z", "F", "M", "S", "D", "P", "X", "N", "E"};
        String[] strRem = { "X", "M", "E", "Z", "F", "N", "A", "S", "D", "P", "E"};
        try {
            System.out.println(treap.toString());
            for (String string : strAdd) {
                treap.insert(string);
            }
            System.out.println(treap.toString());
            System.out.println("##############################################################");
            for (String string : strRem) {
                treap.remove(string);
                System.out.println(treap.toString());
            }
        } catch (Exception e) {
            System.out.println("Database Exception");
            e.printStackTrace();
        }
    }
    public static void testTreapIns(){
        Treap<String> treap = new Treap<String>();
        String[] strAdd = {"A", "Z", "F", "M", "S", "D", "P", "X", "N", "E"};
        try {
            System.out.println(treap.toString());
            for (String string : strAdd) {
                treap.insert(string);
                System.out.println(treap.toString());
            }
        } catch (Exception e) {
            System.out.println("Database Exception");
            e.printStackTrace();
        }
    }
    
    public static void task1() {
        /*
        * You are not given a Main for this task, because we want you to figure out how
        * to do it for yourself.
        * 
        * You are provided with a validTreap() function which will print out valid or
        * invalid for a passed in Treap.
        * 
        * Use this function to make sure that your heaps follow the rules set by the
        * Assignment.
        * 
        * Tip : Create a Main that inserts / deletes a lot of elements and call
        * validTreap after every step
        *//*
    }
    
    public static void task2() {
        /*
         * Note that we also want you to create your own main for this task.
         * 
         * It takes a while to set the DB up, so an example is given below, feel free to
         * change it to test the rest of the functions
         *//*
        String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
        Database db = new Database(columns, 100);
        
        String[][] modules = {
            { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
            { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
            { "UP0102", "Academic orientation 102", "0", "1", "Year" },
            { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
            { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
            { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
            { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
            { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
            { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
            { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
            { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
            { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
            { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
            { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
            { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
            { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
            { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
            { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
            { "PHY263", "General physics 263", "24", "2", "Sem 2" },
            { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
            { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
            { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
            { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
            { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
            { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
                { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
                { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
                { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
                { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
                { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
                { "FSK700", "Physics 700", "135", "4", "Year" }
            };
            
            try {
                for (String[] mod : modules) {
                    db.insert(mod);
                }
                
                db.generateIndexAll();
            } catch (DatabaseException e) {
                System.out.println("Error: " + e);
            }
            
            for (String[] row : db.database) {
                if (row[0] != null) {
                    int c = 0;
                    for (String s : row) {
                        if (c++ == 1) {
                            System.out.print(String.format("%1$-75s", s));
                        } else {
                            System.out.print(s + "\t");
                        }
                    }
                    System.out.println();
                }
            }
            
            System.out.println(db.indexes[0]);
            System.out.println(db.indexes[1]);
    }
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }
    
    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }
        
        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }
        
        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }
        
        if (!validTreap(n.left)) {
            return false;
        }
        
        if (!validTreap(n.right)) {
            return false;
        }
        
        return true;
    }
}

*/