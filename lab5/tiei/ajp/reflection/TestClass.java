package tiei.ajp.reflection;

import java.lang.reflect.*;
import java.util.*;

public class TestClass {

    private static HashMap<String, Object> objectMap = new HashMap<>();
    public static Class<?> clazz;

    public TestClass(Class<?> clazz) {
        this.clazz = clazz;
        System.out.println("当前正在测试的类是：" + clazz.getName());
    }

    public void startInteraction() throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            System.out.println("可用的构造函数及其参数如下：");
            int constructorCount = 0;
            for (Constructor<?> constructor : constructors) {
                constructorCount++;
                System.out.println(constructorCount + ". " + constructor);
            }

            System.out.println("请选择一个构造函数（输入序号），或输入'exit'退出：");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                scanner.close();
                return;
            }
            try {
                int constructorIndex = Integer.parseInt(input);
                Constructor<?> constructor = constructors[constructorIndex - 1];
                if (constructor != null) {
                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    Object[] paramValues = new Object[paramTypes.length];
                    for (int i = 0; i < paramTypes.length; i++) {
                        System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");
                        paramValues[i] = readParameterValue(scanner, paramTypes[i]);
                    }
                    Object instance =  constructor.newInstance(paramValues);

                    System.out.println("是否需要保存这个对象？(yes/no)");
                    String saveOption = scanner.nextLine();

                    if ("yes".equalsIgnoreCase(saveOption)) {
                        System.out.println("请输入对象的名称以保存：");
                        String instanceName = scanner.nextLine();
                        objectMap.put(instanceName, instance);
                    }

                    System.out.println("是否要继续创建对象？(yes/no)");
                    String continueOption = scanner.nextLine();
                    if ("no".equalsIgnoreCase(continueOption)) {
                        break;
                    }
                } else {
                    System.out.println("输入的序号无效，请重新输入。");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入的不是有效的数字，请输入构造函数序号。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("可用的成员方法有：");
        Method[] methods = clazz.getDeclaredMethods();

        while (true) {

            for(int i = 1; i <= methods.length; i++){
                System.out.println(i + ". " + methods[i -1]);
            }
            System.out.println("请选择一个方法（输入序号），或输入'exit'退出：");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                int methodIndex = Integer.parseInt(input);
                Method method = methods[methodIndex - 1];
                if (method != null) {
                    System.out.println("是否使用一个已存在的对象？(yes/no)");
                    String useExistingOption = scanner.nextLine();

                    Object targetObject;
                    if ("yes".equalsIgnoreCase(useExistingOption)) {
                        System.out.println("已保存的对象有：");
                        objectMap.forEach((name, obj) -> System.out.println(name + ": " + obj));
                        System.out.println("请输入对象的名称：");
                        String objectName = scanner.nextLine();
                        targetObject = objectMap.get(objectName);
                    } else {
                        targetObject = createPoint(scanner);
                    }

                    try {
                        // 如果方法没有参数，直接调用
                        if (method.getParameterTypes().length == 0) {
                            Object result = method.invoke(targetObject);
                            System.out.println("方法调用结果：" + result);
                        } else {

                            Class<?>[] paramTypes = method.getParameterTypes();
                            Object[] paramValues = new Object[paramTypes.length];
                            for (int i = 0; i < paramTypes.length; i++) {
                                System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");

                                if(paramTypes[i].getSimpleName().equals("Point")){
                                    System.out.println("是否使用一个已存在的对象？(yes/no)");
                                    String useExistingOption1 = scanner.nextLine();
                                    if ("yes".equalsIgnoreCase(useExistingOption1)) {
                                        System.out.println("已保存的对象有：");
                                        objectMap.forEach((name, obj) -> {
                                            if (obj instanceof Point) {
                                                System.out.println(name + ": " + obj);
                                            }
                                        });
                                        System.out.println("请输入对象的名称：");
                                        String objectName = scanner.nextLine();
                                        paramValues[i] = objectMap.get(objectName);
                                    } else{
                                        System.out.println("请创建对象!");
                                        paramValues[i] = createPoint(scanner);
                                    }
                                }else{
                                    paramValues[i] = readParameterValue(scanner, paramTypes[i]);
                                }
                            }
                            Object result = method.invoke(targetObject, paramValues);
                            System.out.println("方法调用结果：" + result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("输入的序号无效，请重新输入。");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入的不是有效的数字，请输入方法序号。");
            }
        }
        scanner.close();
    }

    private static Object readParameterValue(Scanner scanner, Class<?> paramType) {
        if (paramType == int.class) {
            Object res = scanner.nextInt();
            scanner.nextLine();
            return res;
        } else if (paramType == double.class) {
            Object res = scanner.nextDouble();
            scanner.nextLine();
            return res;
        } else if (paramType == String.class) {
            Object res = scanner.next();
            scanner.nextLine();
            return res;
        } else {
            // 对于其他类型，可以根据需要进行扩展
            System.out.println("不支持的参数类型：" + paramType.getSimpleName());
            return null;
        }
    }

    public static Object createPoint(Scanner scanner) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        clazz = Class.forName("tiei.ajp.reflection.Point");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("可用的构造函数及其参数如下：");
        int constructorCount = 0;
        for (Constructor<?> constructor : constructors) {
            constructorCount++;
            System.out.println(constructorCount + ". " + constructor);
        }

        System.out.println("请选择一个构造函数（输入序号）：");
        String input = scanner.nextLine();

            int constructorIndex = Integer.parseInt(input);
            Constructor<?> constructor = constructors[constructorIndex - 1];

                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] paramValues = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");
                    paramValues[i] = readParameterValue(scanner, paramTypes[i]);
                }
                Object instance =  constructor.newInstance(paramValues);

                System.out.println("是否需要保存这个对象？(yes/no)");
                String saveOption = scanner.nextLine();

                if ("yes".equalsIgnoreCase(saveOption)) {
                    System.out.println("请输入对象的名称以保存：");
                    String instanceName = scanner.nextLine();
                    objectMap.put(instanceName, instance);
                }

                return instance;
    }

    private static Object createLine(Scanner scanner, Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("可用的构造函数及其参数如下：");
        for (int constructorCount = 0; constructorCount < constructors.length; constructorCount++) {
            System.out.println(constructorCount + 1 + ". " + constructors[constructorCount]);
        }
        System.out.println("请选择一个构造函数（输入序号):");
        String input = scanner.nextLine();
        int constructorIndex = Integer.parseInt(input);
        Constructor<?> constructor = constructors[constructorIndex - 1];
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] paramValues = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");
            if(paramTypes[i].getSimpleName().equals("Point")){
                System.out.println("是否使用一个已存在的对象？(yes/no)");
                String option = scanner.nextLine();
                if("yes".equalsIgnoreCase(option)){
                    objectMap.forEach((name, instance) -> System.out.println(name + ": " + instance));
                    System.out.println("请输入对象的名称：");
                    String instanceName = scanner.nextLine();
                    paramValues[i] = objectMap.get(instanceName);
                }else{
                    paramValues[i] = createPoint(scanner);
                }
            }else{
                paramValues[i] = readParameterValue(scanner, paramTypes[i]);
            }
        }
        Object instance =  constructor.newInstance(paramValues);
        System.out.println("是否需要保存这个对象？(yes/no)");
        String saveOption = scanner.nextLine();

        if ("yes".equalsIgnoreCase(saveOption)) {
            System.out.println("请输入对象的名称以保存：");
            String instanceName = scanner.nextLine();
            objectMap.put(instanceName, instance);
        }
        return instance;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(args[0].equals("tiei.ajp.reflection.Point")) {
            try {
                TestClass testClass = new TestClass(Class.forName("tiei.ajp.reflection.Point"));
                testClass.startInteraction();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if(args[0].equals("tiei.ajp.reflection.Line")){
            Class<?> claz = Class.forName("tiei.ajp.reflection.Line");
            System.out.println("当前正在测试的类是："+ claz.getName());
            Scanner scanner = new Scanner(System.in);
            while(true){
                Constructor<?>[] constructors = claz.getDeclaredConstructors();

                System.out.println("可用的构造函数及其参数如下：");
                for (int constructorCount = 0; constructorCount < constructors.length; constructorCount++) {
                    System.out.println(constructorCount + 1 + ". " + constructors[constructorCount]);
                }
                System.out.println("请选择一个构造函数（输入序号），或输入'exit'退出：");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    scanner.close();
                    return;
                }
                int constructorIndex = Integer.parseInt(input);
                Constructor<?> constructor = constructors[constructorIndex - 1];
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] paramValues = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");
                    if(paramTypes[i].getSimpleName().equals("Point")){
                        System.out.println("是否使用一个已存在的对象？(yes/no)");
                        String option = scanner.nextLine();
                        if("yes".equalsIgnoreCase(option)){
                            objectMap.forEach((name, instance) -> System.out.println(name + ": " + instance));
                            System.out.println("请输入对象的名称：");
                            String instanceName = scanner.nextLine();
                            paramValues[i] = objectMap.get(instanceName);
                        }else{
                            paramValues[i] = createPoint(scanner);
                        }
                    }else{
                        paramValues[i] = readParameterValue(scanner, paramTypes[i]);
                    }
                }
                Object instance =  constructor.newInstance(paramValues);
                System.out.println("是否需要保存这个对象？(yes/no)");
                String saveOption = scanner.nextLine();

                if ("yes".equalsIgnoreCase(saveOption)) {
                    System.out.println("请输入对象的名称以保存：");
                    String instanceName = scanner.nextLine();
                    objectMap.put(instanceName, instance);
                }

                System.out.println("是否要继续创建对象？(yes/no)");
                String continueOption = scanner.nextLine();
                if ("no".equalsIgnoreCase(continueOption)) {
                    break;
                }
            }
            System.out.println("可用的成员方法有：");
            Method[] methods = claz.getDeclaredMethods();

            while(true){
                for (int methodCount = 0; methodCount < methods.length; methodCount++) {
                    System.out.println(methodCount + 1 + ". " + methods[methodCount]);
                }
                System.out.println("请选择一个方法（输入序号），或输入'exit'退出：");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    scanner.close();
                    return;
                }
                int methodIndex = Integer.parseInt(input);
                Method method = methods[methodIndex - 1];
                if(method != null){
                    System.out.println("是否使用一个已存在的对象？(yes/no)");
                    String option = scanner.nextLine();

                    Object targetObject;
                    if("yes".equalsIgnoreCase(option)){
                        objectMap.forEach((name, instance) -> System.out.println(name + ": " + instance));
                        System.out.println("请输入对象的名称：");
                        String instanceName = scanner.nextLine();
                        targetObject = objectMap.get(instanceName);
                    }else{
                        targetObject = createLine(scanner, claz);
                    }

                    if (method.getParameterTypes().length == 0) {
                        Object result = method.invoke(targetObject);
                        System.out.println("方法调用结果：" + result);
                    } else {

                        Class<?>[] paramTypes = method.getParameterTypes();
                        Object[] paramValues = new Object[paramTypes.length];
                        for (int i = 0; i < paramTypes.length; i++) {
                            System.out.println("请输入参数 " + paramTypes[i].getSimpleName() + " 的值：");

                            if(paramTypes[i].getSimpleName().equals("Point")){
                                System.out.println("是否使用一个已存在的对象？(yes/no)");
                                String useExistingOption1 = scanner.nextLine();
                                if ("yes".equalsIgnoreCase(useExistingOption1)) {
                                    System.out.println("已保存的对象有：");
                                    objectMap.forEach((name, obj) -> {
                                        if (obj instanceof Point) {
                                            System.out.println(name + ": " + obj);
                                        }
                                    });
                                    System.out.println("请输入对象的名称：");
                                    String objectName = scanner.nextLine();
                                    paramValues[i] = objectMap.get(objectName);
                                } else{
                                    System.out.println("请创建对象!");
                                    paramValues[i] = createPoint(scanner);
                                }
                            }else{
                                paramValues[i] = readParameterValue(scanner, paramTypes[i]);
                            }
                        }
                        Object result = method.invoke(targetObject, paramValues);
                        System.out.println("方法调用结果：" + result);
                    }
                }

            }
        }
    }
}
