/*
 * Copyright 2014 Mike Penz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wqiao.coolweather.typeface;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class CustomFontIcon implements ITypeface {
    private static final String TTF_FILE = "fontello.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "fon";
    }

    @Override
    public String getFontName() {
        return "CustomFontIcon";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "wQiao";
    }

    @Override
    public String getUrl() {
        return "wQiao.me";
    }

    @Override
    public String getDescription() {
        return "wQiao";
    }

    @Override
    public String getLicense() {
        return "";
    }

    @Override
    public String getLicenseUrl() {
        return "";
    }

    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }

    public enum Icon implements IIcon {
        fon_logout('\ue800'),
        fon_login('\ue801'),
        fon_down_open('\ue802'),
        fon_left_open('\ue803'),
        fon_right_open('\ue804'),
        fon_up_open('\ue805'),
        fon_angle_left('\ue806'),
        fon_angle_right('\ue807'),
        fon_angle_up('\ue808'),
        fon_angle_down('\ue809'),
        fon_angle_circled_left('\ue80a'),
        fon_angle_circled_right('\ue80b'),
        fon_angle_circled_up('\ue80c'),
        fon_angle_circled_down('\ue80d'),
        fon_down_big('\ue80e'),
        fon_left_big('\ue80f'),
        fon_right_big('\ue810'),
        fon_up_big('\ue811'),
        fon_th_list('\ue812'),
        fon_th('\ue813'),
        fon_th_large('\ue814'),
        fon_heart_empty('\ue815'),
        fon_comment('\ue816'),
        fon_chat('\ue817'),
        fon_comment_empty('\ue818'),
        fon_chat_empty('\ue819'),
        fon_bell('\ue81a'),
        fon_edit('\ue81b'),
        fon_share('\ue81c'),
        fon_search('\ue81e');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new CustomFontIcon();
            }
            return typeface;
        }
    }
}
