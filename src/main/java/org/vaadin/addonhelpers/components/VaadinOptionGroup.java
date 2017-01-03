package org.vaadin.addonhelpers.components;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;

/**
 * Wrapper around a {@link org.openqa.selenium.WebElement} representing a
 * {@link com.vaadin.v7.ui.OptionGroup}.
 * 
 * @author Daniel Nordhoff-Vergien
 *
 */
public class VaadinOptionGroup {
    private final WebElement optionGroup;

    public static class Option {
        public final boolean selected;
        public final boolean enabeld;
        public final String caption;

        public Option(boolean selected, boolean enabeld, String caption) {
            this.selected = selected;
            this.enabeld = enabeld;
            this.caption = caption;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Option [selected=");
            builder.append(selected);
            builder.append(", enabeld=");
            builder.append(enabeld);
            builder.append(", ");
            if (caption != null) {
                builder.append("caption=");
                builder.append(caption);
            }
            builder.append("]");
            return builder.toString();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((caption == null) ? 0 : caption.hashCode());
            result = prime * result + (enabeld ? 1231 : 1237);
            result = prime * result + (selected ? 1231 : 1237);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Option other = (Option) obj;
            if (caption == null) {
                if (other.caption != null)
                    return false;
            } else if (!caption.equals(other.caption))
                return false;
            if (enabeld != other.enabeld)
                return false;
            if (selected != other.selected)
                return false;
            return true;
        }

    }

    public VaadinOptionGroup(WebElement optionGroup) {
        this.optionGroup = optionGroup;
    }

    /**
     * Returns a list with the options of the option group.
     * 
     * @return List of option group options.
     */
    public List<Option> getOptions() {
        List<WebElement> selectOptions = optionGroup.findElements(By
                .cssSelector(".v-select-option"));
        List<Option> captions = new ArrayList<Option>();
        for (WebElement selectOption : selectOptions) {
            captions.add(new Option(isSelected(selectOption),
                    isEnabled(selectOption), getLabel(selectOption)));
        }
        return captions;
    }

    private boolean isSelected(WebElement selectOption) {
        return selectOption.findElement(By.tagName("input")).isSelected();
    }

    private String getLabel(WebElement selectOption) {
        return selectOption.findElement(By.tagName("label")).getText();
    }

    private boolean isEnabled(WebElement selectOption) {
        String classes = selectOption.getAttribute("class");
        return !StringUtils.contains(classes, "v-disabled");
    }

    /**
     * Does a mouse click on the caption with the given index. The index is the
     * same as in the result of {@link #getOptions()}.
     * 
     * @param index
     *            the (zero based) index of the option to click on.
     */
    public void click(int index) {
        List<WebElement> selectOptions = optionGroup.findElements(By
                .cssSelector(".v-select-option"));
        // click on label as with Valo it is not that simple....
        selectOptions.get(index).findElement(By.tagName("label")).click();
    }
}
