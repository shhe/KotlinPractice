package com.kotlin.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kotlin.practice.basic.BasicType;
import com.kotlin.practice.basic.ControlFlow;
import com.kotlin.practice.basic.ReturnAndJumps;
import com.kotlin.practice.classandobject.ClassAndInheritance;
import com.kotlin.practice.classandobject.Const;
import com.kotlin.practice.classandobject.DataClasses;
import com.kotlin.practice.classandobject.DataProviderManager;
import com.kotlin.practice.classandobject.DelegatedProperties;
import com.kotlin.practice.classandobject.Delegation;
import com.kotlin.practice.classandobject.EnumClasses;
import com.kotlin.practice.classandobject.Extensions;
import com.kotlin.practice.classandobject.Generics;
import com.kotlin.practice.classandobject.MyClass;
import com.kotlin.practice.classandobject.Outer1;
import com.kotlin.practice.classandobject.Person;
import com.kotlin.practice.classandobject.PropertiesAndFields;
import com.kotlin.practice.classandobject.Subclass;
import com.kotlin.practice.classandobject.Sum;
import com.kotlin.practice.classandobject.TestEval;
import com.kotlin.practice.classandobject.User;
import com.kotlin.practice.classandobject.User1;
import com.kotlin.practice.function_lambdas.Functions;
import com.kotlin.practice.function_lambdas.Lambdas;
import com.kotlin.practice.other.NullSafety;
import com.kotlin.practice.other.Ranges;
import com.kotlin.practice.other.Reflection;
import com.kotlin.practice.other.StandardPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new BasicType().base();

        new BasicType().test();

        new BasicType().test2();

        new BasicType().transform();

        new BasicType().compare();

        new BasicType().decimalDigitValue('2');

        new BasicType().nullableList_test();

        Log.i(TAG, "decimalDigitValue: "+ new BasicType().decimalDigitValue('2'));

        new BasicType().array();

        new BasicType().string();



        ControlFlow controlFlow = new ControlFlow();

        Log.i(TAG, ""+controlFlow.hasPrefix("prefix"));

        Log.i(TAG, controlFlow.when_exp2(2));

        controlFlow.when_exp();

        controlFlow.for_exp1();

        controlFlow.while_or_doWhile(3);

        Log.i(TAG, "elvis_operators "+ new ReturnAndJumps().elvis_operators(null));

        Log.i(TAG, "elvis_operators: "+ new ReturnAndJumps().foo(new Node()));

        new ReturnAndJumps().foo();

        new ReturnAndJumps().foo2();
        new ReturnAndJumps().foo3();
        new ReturnAndJumps().foo4();
        new ReturnAndJumps().foo5();

        new ClassAndInheritance.InitOrderDemo("123");

        Log.i(TAG, new ClassAndInheritance.Customer("abc").getCustomer());

        new ClassAndInheritance.Constructors(1);
//        new ClassAndInheritance.DontCreateMe();

        ClassAndInheritance.Foo foo = new ClassAndInheritance.Foo();
//        foo.setX(4);
//        Log.i(TAG, "foo: " + foo.getX());

        ClassAndInheritance.Bar1 bar1 = new ClassAndInheritance.Bar1();
        bar1.setX(2);
        Log.i(TAG, "bar1: " + bar1.getX());

        ClassAndInheritance.Bar2 bar2 = new ClassAndInheritance.Bar2(5);
        Log.i(TAG, "bar2: " + bar2.getCount());

        ClassAndInheritance.Derived3 derived3 = new ClassAndInheritance.Derived3("li", "ming");
        Log.i(TAG, derived3.getName() + " "+ derived3.getLastName() + " " +derived3.getSize());

        ClassAndInheritance.Bar4 bar4 = new ClassAndInheritance.Bar4();
        bar4.f();

        ClassAndInheritance.C c = new ClassAndInheritance.C();
        c.f();
        c.b();


        PropertiesAndFields.Address address = new PropertiesAndFields.Address();
        address.setName("name");

        PropertiesAndFields propertiesAndFields = new PropertiesAndFields();
        propertiesAndFields.setSize(0);
        Log.i(TAG, "PropertiesAndFields "+propertiesAndFields.isEmpty());

        PropertiesAndFields propertiesAndFields1 = new PropertiesAndFields();
        propertiesAndFields1.getSetterWithAnnotation();
        propertiesAndFields1.setCounter(4);
        Log.i(TAG, "propertiesAndFields1 "+propertiesAndFields1.getCounter());

        PropertiesAndFields.MyTest myTest = new PropertiesAndFields.MyTest();
//        myTest.getSubject();

        Subclass subclass = new Subclass();
        Log.i(TAG, "subclass "+subclass.getD());

        Extensions extensions = new Extensions();
        extensions.test_swap();
        //这个例子会打印出“c”，因为被调用的扩展函数只依赖于参数c所声明的类型，即 C 这个类。
        extensions.printFoo(new Extensions.D());

        // 成员的扩展
        extensions.test();

        List<Integer> list = new ArrayList<Integer>();
        List<Integer> otherList = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        otherList.add(1);
        otherList.add(3);
        otherList.add(5);
        otherList.add(6);
        // Java
        Collections.swap(list, Collections.binarySearch(list,
                Collections.max(otherList)), list.lastIndexOf(Collections.max(list)));



        User user = new User("jons", 21);
        Log.i(TAG, user.toString());
        user.getAge();
        user.getName();

        User1 user1 = new User1();

        Person person = new Person("LiLei");
        person.getName();
        person.setAge(15);
        Person person1 = new Person("LiLei");
        person1.setAge(20);
        // 虽然两个 Person 会有不同的年龄，但是他们会被识为相等。
        Log.i(TAG, "person.equals : "+person.equals(person1));

        DataClasses dataClasses = new DataClasses();
        dataClasses.copyTest();

        Sum sum = new Sum(new Sum(new Const(1), new Const(2)), new Const(2));
        Log.i(TAG, "Sum: " + new TestEval().eval(sum));

        Generics.Box<Integer> box = new Generics.Box<>(1);
        Log.i(TAG, box.getValue().toString());

        Generics generics = new Generics();
        generics.test();


        Log.i(TAG, "Nested().foo(): "+ new Outer1.Nested().foo());

        EnumClasses.ProtocolState protocolState = EnumClasses.ProtocolState.TALKING;

        /**
         * 每一个枚举常量都有属性来获取它的名字以及在枚举类声明中的位置：
         *
         * val name: String
         * val ordinal: Int
         */
        protocolState.name();
        protocolState.ordinal();

        /**
         * 与 Java 一样，Kotlin 的枚举类提供了合成的（synthetic）方法，可以列出所有定义的枚举常量以及根据名字获取枚举常量。这些方法的签名如下（假设枚举类的名字是 EnumClass）：
         *
         * EnumClass.valueOf(value: String): EnumClass
         * EnumClass.values(): Array<EnumClass>
         */
        Log.i(TAG, "protocolState: "+ protocolState.name() + " ordinal: " + protocolState.ordinal() + " TALKING: "+EnumClasses.ProtocolState.valueOf("TALKING").name());

        EnumClasses enumClasses = new EnumClasses();
        enumClasses.test();

        DataProviderManager.INSTANCE.registerDataProvider(new DataProviderManager.DataProvider());

        MyClass.Factory.create();

        new Delegation.Derived(new Delegation.BaseImpl(10)).print();
        new Delegation.Derived1(new Delegation.BaseImpl1(10)).printMessage();

        DelegatedProperties.Example example = new DelegatedProperties.Example();
        Log.i(TAG, example.getP());
        example.setP("new");
        Log.i(TAG, example.getP());

        DelegatedProperties properties = new DelegatedProperties();
        Log.i(TAG, properties.getLazyValue());
        Log.i(TAG, properties.getLazyValue());

        DelegatedProperties.User user2 = new DelegatedProperties.User();
        user2.setName("first");
        user2.setName("second");
        Log.i(TAG, user2.getName());

        properties.printUser();




        Functions functions = new Functions();
        functions.testFoo();
        double retur = functions.double2(20);
        Log.i(TAG, "functions.double2: "+retur);

        Lambdas lambdas = new Lambdas();
        lambdas.test();
        lambdas.test3();
        lambdas.test4();

        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        lambdas.test7(list);
        Log.i(TAG, ""+lambdas.test7(list1));

        lambdas.test8();
        lambdas.test10();



        com.kotlin.practice.other.Collections coll = new com.kotlin.practice.other.Collections();
        coll.test();
        coll.testMap();

        Ranges ranges = new Ranges();
        ranges.test();

        Reflection reflection = new Reflection();
        reflection.test2();
        reflection.test3();
        reflection.test4();


        NullSafety nullSafety = new NullSafety();
        nullSafety.test();


        StandardPractice practice = new StandardPractice();
        practice.runTest();
        practice.runTest2();
        practice.withTest();
        practice.withTest2();
        practice.applyTest();
        practice.alsoTest2();
        practice.letTest();
        practice.takeIfTest();
        practice.repeatTest();
    }

    private void test_java_generics() {

        // Java
        List<String> strs = new ArrayList<String>();
        /*List<Object> objs = strs; // !!! The cause of the upcoming problem sits here. Java prohibits this!
        objs.add(1);    // Here we put an Integer into a list of Strings
        String s= strs.get(0);*/  // !!! ClassCastException: Cannot cast Integer to String
    }

    // Java
    /*void copyAll(Collections<Object> to, Collections<String> from) {
        to.addAll(from);    // !!! Would not compile with the naive declaration of addAll:
        // Collection<String> is not a subtype of Collection<Object>
    }*/

    interface Collection<E>  {
        void addAll(Collection<? extends E> items);
    }

    // Java
    interface Source<T> {
        T nextT();
    }
    void demo(Source<String> strs) {
//        Source<Object> objects = strs;  // !!! Not allowed in Java
        Source<? extends Object> objects = strs;
        // ...
    }
}