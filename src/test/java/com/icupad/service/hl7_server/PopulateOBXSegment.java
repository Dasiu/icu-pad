/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is "PopulateOBXSegment.java".  Description:
 * "Example Code"
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Contributor(s): James Agnew
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  �GPL�), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 *
 */

package com.icupad.service.hl7_server;

import ca.uhn.hl7v2.HL7Exception;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Example code for populating an OBX segment
 *
 * @author <a href="mailto:jamesagnew@sourceforge.net">James Agnew</a>
 * @version $Revision: 1.1 $ updated on $Date: 2009-03-19 13:09:26 $ by $Author: jamesagnew $
 */
public class PopulateOBXSegment {
    public static void main(String[] args) throws HL7Exception, IOException, ParseException {
        Locale locale = new Locale("pl", "PL");
        NumberFormat instance = NumberFormat.getInstance(locale);
        Number parse = instance.parse("1,2").doubleValue();
        double v = instance.parse(",2").doubleValue();
        System.out.println(parse);
        System.out.println(v);
    }
}
