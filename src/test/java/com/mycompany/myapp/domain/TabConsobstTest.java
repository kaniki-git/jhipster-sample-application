package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabConsobstTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabConsobst.class);
        TabConsobst tabConsobst1 = new TabConsobst();
        tabConsobst1.setId(1L);
        TabConsobst tabConsobst2 = new TabConsobst();
        tabConsobst2.setId(tabConsobst1.getId());
        assertThat(tabConsobst1).isEqualTo(tabConsobst2);
        tabConsobst2.setId(2L);
        assertThat(tabConsobst1).isNotEqualTo(tabConsobst2);
        tabConsobst1.setId(null);
        assertThat(tabConsobst1).isNotEqualTo(tabConsobst2);
    }
}
