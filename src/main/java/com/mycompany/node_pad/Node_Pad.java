package com.mycompany.node_pad;

import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import static java.nio.file.Files.size;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.plaf.ToolBarUI;
import static javax.swing.text.StyleConstants.Size;
import javax.swing.undo.UndoManager;

public class Node_Pad extends JFrame {

    private JMenuBar mBar;
    private JMenu mFile, mEdit, mFormat, mView, mZoom, mHelp;
    private JMenuItem itemNew, itemNewwindow, itemOpen, itemSave, itemSaveas, itemPagesetup, itemPrint, itemExit;
    private JMenuItem itemUndo, itemCut, itemCopy, itemDelete, itemPaste, itemSearch, itemFind, itemFindtext, itemFindprevious, itemReplace, itemGoto, itemSelectall, itemTime;
    private JMenuItem itemZoomin, itemZoomuot, itemRestore;
    private JMenuItem itemViewhelp, itemSendfeedback, itemAboutnotepad;
    private JButton btnnew, btnopen, btnsave;
    private JCheckBoxMenuItem itemWrap, itemFont, itemStatus;
    private JTextArea txtEditor;
    private UndoManager undoManager;
    private  JToolBar toolbar;

    public Node_Pad(String title) {
        super(title);
        createMenu();
        createGUI();
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createtoolbar();
    }

    private void createMenu() {
        mBar = new JMenuBar();

        // Create Menus
        mFile = new JMenu("File");
        mEdit = new JMenu("Edit");
        mFormat = new JMenu("Format");
        mView = new JMenu("View");
        mHelp = new JMenu("Help");

        // Add Menus to MenuBar
        mBar.add(mFile);
        mBar.add(mEdit);
        mBar.add(mFormat);
        mBar.add(mView);
        mBar.add(mHelp);

        // Add MenuItems to "File" Menu
        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemNewwindow = new JMenuItem("New Window"));
        mFile.add(itemOpen = new JMenuItem("Open"));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveas = new JMenuItem("Save As"));
        mFile.addSeparator();

        mFile.add(itemPagesetup = new JMenuItem("Page Set Up"));
        mFile.add(itemPrint = new JMenuItem("Print"));
        mFile.addSeparator();

        mFile.add(itemExit = new JMenuItem("Exit"));

        // Add MenuItems to "Edit" Menu
        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));
        mEdit.addSeparator();
        mEdit.add(itemSearch = new JMenuItem("Search With Bing"));
        mEdit.add(itemFind = new JMenuItem("Find"));
        mEdit.add(itemFindtext = new JMenuItem("Find Text"));
        mEdit.add(itemFindprevious = new JMenuItem("Find Previous"));
        mEdit.add(itemReplace = new JMenuItem("Replace"));
        mEdit.add(itemGoto = new JMenuItem("Go To"));
        mEdit.addSeparator();
        mEdit.add(itemSelectall = new JMenuItem("Select All"));
        mEdit.add(itemTime = new JMenuItem("Time/Date"));

        // Add MenuItems to "Format" Menu
        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap"));
        mFormat.add(itemFont = new JCheckBoxMenuItem("Font"));

        mView.add(mZoom = new JMenu("Zoom"));
        mZoom.add(itemZoomin = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomuot = new JMenuItem("Zoom Out"));
        mView.add(itemStatus = new JCheckBoxMenuItem("Status Bar", true));

        mHelp.add(itemViewhelp = new JMenuItem("View Help"));
        mHelp.add(itemSendfeedback = new JMenuItem("Send Feed Back"));
        mHelp.add(itemAboutnotepad = new JMenuItem("About Note Pad"));

        // Create keyboard shortcuts
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemNewwindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
//        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent., KeyEvent.CTRL_DOWN_MASK));
        itemSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemFindtext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_DOWN_MASK));
        itemFindprevious.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK));

        itemTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK));

        // Add action listener for "Exit"
        itemExit.addActionListener((ActionEvent e) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you sure to Exit") == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Attach the MenuBar to the frame
        setJMenuBar(mBar);

        // Edit Mfile
        itemNew.addActionListener(e -> nevv());
        itemNewwindow.addActionListener(e -> newWindow());
        itemOpen.addActionListener(e -> open());
        itemSave.addActionListener(e -> save());
        itemSaveas.addActionListener(e -> saveAs());
        itemPagesetup.addActionListener(e -> pageSetup());
        itemPrint.addActionListener(e -> print());

        // Action Listeners
        itemUndo.addActionListener(e -> undo());
        itemCut.addActionListener(e -> txtEditor.cut());
        itemCopy.addActionListener(e -> txtEditor.copy());
        itemPaste.addActionListener(e -> txtEditor.paste());
        itemDelete.addActionListener(e -> delete());
        itemWrap.addActionListener(e -> WordWrap());

    }

    private void createGUI() {
        txtEditor = new JTextArea();
        JScrollPane sp = new JScrollPane(txtEditor);
        add(sp);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 20));

        // Undo Manager
        undoManager = new UndoManager();
        txtEditor.getDocument().addUndoableEditListener(undoManager);
    }
////////////////////////////////////////////////////////

    private void nevv() {
        txtEditor.setText(""); // Xoá hết văn bản
    }

    private void newWindow() {
        Node_Pad newNotepad = new Node_Pad("Notepad");
        newNotepad.setVisible(true); // làm mới note pad
    }

    private void open() {
        JFileChooser dlg = new JFileChooser();
        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            try {
            FileInputStream fis = new FileInputStream(dlg.getSelectedFile());
            byte[] b = new byte[fis.available()];
            fis.read(b);
            txtEditor.setText(new String(b));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đọc File!!");
        }
    }

    private void save() {
        JFileChooser dlg = new JFileChooser();
        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            try {
            FileOutputStream fos = new FileOutputStream(dlg.getSelectedFile());
            fos.write(txtEditor.getText().getBytes());
            fos.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đọc File!!");
        }
    }

    private void saveAs() {
        JFileChooser dlg = new JFileChooser();
        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            try {
            FileOutputStream fos = new FileOutputStream(dlg.getSelectedFile());
            fos.write(txtEditor.getText().getBytes());
            fos.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi đọc File!!");
        }
    }

    private void pageSetup() {
        String marginStr = JOptionPane.showInputDialog(this, "Nhập lề (top, left, bottom, right) bằng pixel được phân cách bằng dấu phẩy(0,0,0,0):", "Thiết lập trang", JOptionPane.PLAIN_MESSAGE);

        if (marginStr != null && !marginStr.isEmpty()) {
            try {
                // Parse the margin values
                String[] margins = marginStr.split(",");
                if (margins.length == 4) {
                    int top = Integer.parseInt(margins[0].trim());
                    int left = Integer.parseInt(margins[1].trim());
                    int bottom = Integer.parseInt(margins[2].trim());
                    int right = Integer.parseInt(margins[3].trim());

                    // Set margins in the JTextArea
                    txtEditor.setMargin(new Insets(top, left, bottom, right));
                } else {
                    JOptionPane.showMessageDialog(this, "Nhập chính xác khoảng cách 4 giá trị trên!.", "không hợp lệ??", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá trị không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void print() {
        PrinterJob pr = PrinterJob.getPrinterJob();
        pr.setJobName("Print Document");

        // Thiết lập nội dung có thể in (JTextArea)
        pr.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE; // Chỉ có một trang có sẵn
            }
            // Dịch bản in để căn chỉnh đúng trên giấy
            graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            // In nội dung vùng văn bản
            txtEditor.print(graphics);
            return PAGE_EXISTS;
        });

        // Hiển thị hộp thoại in và tiến hành in nếu người dùng chấp nhận
        if (pr.printDialog()) {
            try {
                pr.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Print failed: " + e.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
//////////////////////////////////////////////

    private void undo() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    private void delete() {
        // Nhận vị trí bắt đầu và kết thúc của văn bản đã chọn
        int start = txtEditor.getSelectionStart();
        int end = txtEditor.getSelectionEnd();

        // Nếu có một số văn bản được chọn, hãy xóa nó
        if (start != end) {
            txtEditor.replaceRange("", start, end);
        }
    }

    private void WordWrap() {
        txtEditor.setLineWrap(itemWrap.isSelected());
        txtEditor.setWrapStyleWord(itemWrap.isSelected());
    }

    public static void main(String[] args) {
        Node_Pad notepad = new Node_Pad("Notepad");
        notepad.setVisible(true);
    }
/////////////////////////////////

    private void createtoolbar() {
        toolbar = new JToolBar();
        toolbar.add(btnnew = new JButton("New"));
        toolbar.add(btnopen = new JButton("Open"));
        toolbar.add(btnsave = new JButton("Save"));
        add(toolbar, BorderLayout.NORTH);
        btnnew.setIcon(new ImageIcon(this.getClass().getResource("/images/new.png")));
        btnopen.setIcon(new ImageIcon(this.getClass().getResource("/images/open.png")));
        btnsave.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));

    }
}
