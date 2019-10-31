package com.crystal.pattern.singleton;

public class LazyInnerClassSigleton {

    static {
        System.out.println("静态方法块");
    }
    
    {
        System.out.println("方法快");
    }
    
    private LazyInnerClassSigleton() {
        System.out.println("构造方法");
        if (LazyInnerClass.innerClassSigleton != null) {
            throw new RuntimeException("已经初始化单例对象");
        }
    }

    public static final LazyInnerClassSigleton getInstance() {
        // 在返回结果之前先加载内部类
        System.out.println("getInstance");
        return LazyInnerClass.innerClassSigleton;
    }

    private static class LazyInnerClass {
        private static final LazyInnerClassSigleton innerClassSigleton = new LazyInnerClassSigleton();
        
        static {
            System.out.println("内部类静态方法块");
        }
        
        {
            System.out.println("内部类方法块");
        }
    }
}
