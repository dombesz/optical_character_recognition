package optical_character_recognition;

// ========================================================================== ;
//     MODIFIED FROM GraphGNG.java
//     whose documentation follows                                            ;
//     Copyright (1996-1997)  Hartmut S. Loos                                 ;
//                                                                            ;
//     Institut f"ur Neuroinformatik   ND 03                                  ;
//     Ruhr-Universit"at Bochum                                               ;
//     44780 Bochum                                                           ;
//                                                                            ;
//     Tel  : +49 234 7007845                                                 ;
//     Email: loos@neuroinformatik.ruhr-uni-bochum.de                         ;
//                                                                            ;
//     For version information and parameter explanation have a look at       ;
//     the file 'DemoGNG.java'.                                               ;
//                                                                            ;
// ========================================================================== ;
//                                                                            ;
// Copyright 1996-1997 Hartmut S. Loos                                        ;
//                                                                            ;
// This program is free software; you can redistribute it and/or modify       ;
// it under the terms of the GNU General Public License as published by       ;
// the Free Software Foundation; either version 1, or (at your option)        ;
// any later version.                                                         ;
//                                                                            ;
// This program is distributed in the hope that it will be useful,            ;
// but WITHOUT ANY WARRANTY; without even the implied warranty of             ;
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              ;
// GNU General Public License for more details.                               ;
//                                                                            ;
// You should have received a copy of the GNU General Public License          ;
// along with this program; if not, write to the Free Software                ;
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                  ;
//                                                                            ;
// ========================================================================== ;

// Changed by Sebastien Baehni in order to be deprecative compliant.
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * A class implementing the error graph.
 *
 */
class ErrorGraph extends Panel {

    SelGraphics graph;
    TextField error;
    JButton clear;
    /**
     * The name of the clear button.
     */
    protected final static String CLEAR = "Clear";

    /**
     * The name of the close button.
     */
    //protected final static String CLOSE = "Close";
    ErrorGraph() {

        graph = new SelGraphics();

        setLayout(new BorderLayout());
        add("North", new Label("  Error Graph"));
        add("Center", graph);
        Panel pSouth = new Panel();
        clear = new JButton(CLEAR);
        ActionListener clearListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                clearPerformed();
            }
        };
        clear.addActionListener(clearListener);
        pSouth.add(clear);
        add("South", pSouth);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(Math.abs(dimension.width - this.getSize().width), Math.abs(dimension.height - this.getSize().height));
    }

    public void clearPerformed() {
        graph.clear();
    }
}
