package views.cell;

/**
 *
 * @author RAVEN
 */
public interface TableActionEvent {

    public void onEdit(int row);

    public void onDelete(int row);

    public void onShow(int row);
}
