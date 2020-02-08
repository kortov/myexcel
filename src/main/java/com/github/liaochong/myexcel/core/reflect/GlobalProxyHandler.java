/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.myexcel.core.reflect;

import com.github.liaochong.myexcel.utils.ReflectUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaochong
 * @version 1.0
 */
public final class GlobalProxyHandler {

    private static int INDEX = 0;

    private static final Map<Class, Integer> PROXY_CACHE = new HashMap<>();

    public static synchronized void createProxy(Class dataType) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.github.liaochong.myexcel.core.reflect.GlobalProxyImpl");

        Integer index = PROXY_CACHE.get(dataType);
        if (index != null) {
            return;
        }
        if (INDEX > 10) {
            INDEX = 999;
        }
        CtMethod reset = cc.getDeclaredMethod("reset" + INDEX);

        ClassFieldContainer classFieldContainer = ReflectUtil.getAllFieldsOfClass(dataType);
        StringBuilder resetBody = new StringBuilder();
        parse(classFieldContainer, resetBody);
        reset.setBody("{}");

        PROXY_CACHE.put(dataType, index);
        if (INDEX != 999) {
            INDEX++;
        }
    }

    private static void parse(ClassFieldContainer classFieldContainer, StringBuilder stringBuilder) {
        ClassFieldContainer parent = classFieldContainer.getParent();
        if (parent != null) {
            parse(parent, stringBuilder);
        }
        List<Field> fields = classFieldContainer.getFields();
        for (Field field : fields) {

        }
    }
}
