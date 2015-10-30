package com.dream.library.widgets.edit_text_validator;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;
import com.dream.library.utils.AbStringUtils;

/**
 * Author:      SuSong
 * Email:       751971697@qq.com | susong0618@163.com
 * GitHub:      https://github.com/susong0618
 * Date:        15/10/29 上午11:01
 * Description: SmallChaser
 */
@SuppressWarnings("unused")
public class UserNameValidator extends Validator {
    public UserNameValidator(String _customErrorMessage) {
        super(_customErrorMessage);
    }

    @Override
    public boolean isValid(EditText et) {
        return AbStringUtils.LEGAL_NAME.matcher(et.getText().toString().trim()).matches();
    }
}
