package com.dream.library.widgets.edit_text_validator;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;
import com.dream.library.utils.AbStringUtils;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/30 上午10:02
 * Description: EasyFrame
 */
public class PasswordValidator extends Validator {
    public PasswordValidator(String _customErrorMessage) {
        super(_customErrorMessage);
    }

    @Override
    public boolean isValid(EditText et) {
        return AbStringUtils.LEGAL_PASSWORD.matcher(et.getText().toString().trim()).matches();
    }
}
