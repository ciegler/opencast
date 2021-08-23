import React from "react";
import {useTranslation} from "react-i18next";
import {Field} from "formik";
import WizardNavigationButtons from "../../../shared/wizard/WizardNavigationButtons";

const AclMetadataPage = ({ nextPage, formik, isEdit }) => {
    const { t } = useTranslation();
    return (
        <>
            <div className="modal-content">
                <div className="modal-body">
                    <div className="full-col">
                        <ul>
                            <li>
                                <div className="obj tbl-details">
                                    <header>{t('USERS.ACLS.NEW.METADATA.TITLE')}</header>
                                    <div className="obj-container">
                                        <table className="main-tbl">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        {t('USERS.ACLS.NEW.METADATA.NAME.CAPTION')}
                                                        <i className="required">*</i>
                                                    </td>
                                                    <td>
                                                        <Field className="hidden-input"
                                                               name="name"
                                                               tabIndex="1"
                                                               placeholder={t('USERS.ACLS.NEW.METADATA.NAME.PLACEHOLDER')}/>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            {/* Button for navigation to next page */}
            {!isEdit && (
                <WizardNavigationButtons isFirst
                                         formik={formik}
                                         nextPage={nextPage}/>
            )}
        </>
    );
};

export default AclMetadataPage;
